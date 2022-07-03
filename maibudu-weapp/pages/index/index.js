import invoke from '../../utils/http'
import {
    gotoPage,
    showNotify,
    showError
} from '../../utils/common'
Page({
    data: {
        shareCode: '-',
        bookCount: 0,
        showBookScanOverlay: false,
        showBookScanResultDialog: false,
        bookSlimInfo: {
            id: null,
            title: '',
            author: '',
            publisher: '',
            cover: ''
        },
        showRegisterDialog: false,
        avatarUrl: 'cloud://prod-3ggnrc2pfd5a1785.7072-prod-3ggnrc2pfd5a1785-1312587653/avatar/default_avatar.png',
        nickname: ''
    },

    /**
     * 跳转我的书架页
     */
    goBookshelf() {
        gotoPage('../bookshelf/index')
    },
    

    /**
     * 跳转共享书架页
     */
    goShare() {
        gotoPage('../share/index')
    },

    /**
     * 书籍信息确认弹窗关闭时
     */
    onBookScanResultDialogClose() {
        this.setData({
            showBookScanResultDialog: false,
            bookSlimInfo: {
                title: '',
                author: '',
                publisher: '',
                cover: ''
            }
        })
    },
    
    /**
     * 书籍信息确认弹窗确认时
     */
    async onBookScanResultDialogConfirm() {
        if (!this.data.bookSlimInfo.id) {
            return
        }
        const res = await invoke({
            path: '/api/shelf/addBook?bookId=' + this.data.bookSlimInfo.id
        })
        if (res) {
            showNotify('书籍添加成功')
            // 刷新书籍总数
            this.getBookCount()
        }
        this.onBookScanResultDialogClose()
    },

    /**
     * 导入共享书架
     */
    async importShare(e) {
        const shareCode = e.detail.keyword
        if (!shareCode) {
            return;
        }
        const success = await invoke({
            path: '/api/share/import?shareCode=' + shareCode
        })
        if (success) {
            showNotify('导入共享书架成功')
        }
    },

    /**
     * 调起相机扫码
     */
    scanBook() {
        try {
            this.setData({
                showBookScanOverlay: true
            })
            let that = this
            wx.scanCode({
                onlyFromCamera: false, // 允许从相册选择照片
                scanType: ['barCode'], // 只允许扫条形码，不能扫二维码
                success: async function (res) {
                    const book = await invoke({
                        path: '/api/book/scan?isbn=' + res.result
                    })
                    that.setData({
                        bookSlimInfo: {
                            id: book.id,
                            title: book.title,
                            author: book.author || '未知',
                            publisher: book.publisher || '未知',
                            cover: book.cover
                        },
                        showBookScanResultDialog: true,
                        showBookScanOverlay: false
                    })
                },
                fail: function () {
                    showError('书籍扫描失败，请再试试~')
                }
            })
        } finally{
            this.setData({
                showBookScanOverlay: false
            })
        }
    },

    /**
     * 用户注册弹窗
     */
    doRegister() {
        this.setData({
            showRegisterDialog: true
        })
    },

    /**
     * 获取微信头像回调，上传到云存储，换取标准URL
     */
    onChooseAvatar(e) {
        let that = this
        wx.cloud.uploadFile({
            cloudPath: 'avatar/' + wx.getStorageSync('uid') + '.jpeg',
            filePath: e.detail.avatarUrl,
            success: function(res) {
                const fileID = res.fileID
                wx.cloud.getTempFileURL({
                    fileList: [fileID],
                    success: function(res) {
                        const url = res.fileList[0].tempFileURL
                        that.setData({
                            avatarUrl: url
                        })
                    },
                    fail: function(err) {
                        showError('头像保存失败了，请再试试~')
                    }
                })
            },
            fail: function(err) {
                showError('头像保存失败啦，请再试试~')
            }
        })
    },

    /**
     * 用户注册弹窗关闭
     */
    onRegisterDialogClose() {
        this.setData({
            avatarUrl: 'cloud://prod-3ggnrc2pfd5a1785.7072-prod-3ggnrc2pfd5a1785-1312587653/avatar/default_avatar.png',
            nickname: ''
        })
    },

    /**
     * 用户注册弹窗确认
     */
    async onRegisterDialogConfirm() {
        const user = await invoke({
            path: '/api/user/register',
            method: 'POST',
            data: {
                nickname: this.data.nickname,
                avatar: this.data.avatarUrl
            }
        })
        if (user) {
            wx.setStorageSync('shareCode', user.code)
            wx.setStorageSync('nickname', user.nickname)
            wx.setStorageSync('avatar', user.avatar)
            this.loadShareCode()
        }
    },

    onLoad() {
        this.loadShareCode()
        this.getBookCount()
    },

    /**
     * 获取书籍总数
     */
    async getBookCount() {
        const count = await invoke({
            path: '/api/shelf/count'
        })
        this.setData({
            bookCount: count
        })
    },

    /**
     * 读取共享码
     */
    loadShareCode() {
        let shareCode = wx.getStorageSync('shareCode')
        if (!shareCode) {
            setTimeout(() => {
                shareCode = wx.getStorageSync('shareCode')
                if (shareCode) {
                    this.setData({
                        shareCode: shareCode
                    })
                }
            }, 500)
        } else {
            this.setData({
                shareCode: shareCode
            })
        }
    }

})