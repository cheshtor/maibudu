import invoke from '../../utils/http'
import {
    gotoPage,
    showNotify
} from '../../utils/common'

Page({
    data: {
        shareCode: '-',
        bookCount: 0,
        showBookScanResultDialog: false,
        bookSlimInfo: {
            title: '',
            author: '',
            publisher: '',
            cover: ''
        }
    },

    /**
     * 跳转我的书架页
     */
    goBookshelf() {
        // gotoPage('../bookshelf/index')
        this.setData({
            showBookScanResultDialog: true
        })
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
            showBookScanResultDialog: false
        })
    },
    
    /**
     * 籍信息确认弹窗确认时
     */
    onBookScanResultDialogConfirm() {
        console.log('书籍加入书架')
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
                        name: book.name,
                        author: book.author,
                        publisher: book.publisher,
                        cover: book.cover
                    },
                    showBookScanResultDialog: true
                })
            },
            fail: function () {

            }
        })
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