import invoke from "../../utils/http"
import {gotoPage, showNotify} from '../../utils/common'
Page({

    data: {
        shelves: [], // 书架列表
        loadShelfLock: false, // 加载书架列表锁，确保每一次加载流程走完之前不会发起下一次请求
        page: { // 分页信息
            pageNo: 1,
            pageSize: 10,
            totalCount: 0,
            totalPages: 0
        }
    },

    /**
     * 加载书籍列表
     */
    async loadShelves(pageNo, pageSize) {
        if (this.data.loadShelfLock) {
            return
        }
        try {
            this.data.loadShelfLock = true
            const res = await invoke({
                path: `api/share/list?pageNo=${pageNo}&pageSize=${pageSize}`
            })
            this.setData({
                shelves: this.data.shelves.concat(res.rows),
                page: {
                    pageNo: res.pageNo,
                    pageSize: res.pageSize,
                    totalCount: res.totalCount,
                    totalPages: res.totalPages
                }
            })
        } finally {
            this.data.loadShelfLock = false
        }
    },

    onLoad(options) {
        this.loadShelves(this.data.page.pageNo, this.data.page.pageSize)
    },

    /**
     * 点击共享书架，跳转搜索页面
     */
    onClickShelf(e) {
        const shareCode = e.detail.shareCode
        const nickname = e.detail.nickname
        gotoPage('../bookshelf/index', {
            shareCode: shareCode,
            nickname: nickname
        })
    },

    /**
     * 删除共享书架
     */
    async onRemoveShelf(e) {
        const shareCode = e.detail.shareCode
        const success = await invoke({
            path: '/api/share/remove?shareCode=' + shareCode
        })
        if (success) {
            this.setData({
                shelves: [],
                page: {
                    pageNo: 1,
                    pageSize: 10,
                    totalCount: 0,
                    totalPages: 0
                }
            })
            this.onLoad()
            showNotify('共享书架删除成功')
        }
    },

    /**
     * 滚动到底部时触发
     */
    onScrollToBottom() {
        if (this.data.page.pageNo === this.data.page.totalPages) {
            return
        }
        const nextPage = parseInt(this.data.page.pageNo) + 1
        this.loadShelves(nextPage, this.data.page.pageSize)
    }
})