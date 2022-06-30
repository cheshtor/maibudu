import invoke from '../../utils/http'
import {
    gotoPage,
    showNotify
} from '../../utils/common'


Page({
    data: {
        shareCode: '-',
        bookCount: 0
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
        wx.scanCode({
            onlyFromCamera: false, // 允许从相册选择照片
            scanType: ['barCode'], // 只允许扫条形码，不能扫二维码
            success: async function (res) {
                const response = await invoke({
                    path: '/api/book/scan?isbn=' + res.result
                })
                console.log(response)
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