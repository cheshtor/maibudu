import invoke from "../../utils/http"

Page({
    data: {
        books: [], // 书籍列表
        keywords: '', // 搜索关键字
        loadBooksLock: false, // 加载书籍列表锁，确保每一次加载流程走完之前不会发起下一次请求
        page: { // 分页信息
            pageNo: 1,
            pageSize: 10,
            totalCount: 0,
            totalPages: 0
        }
    },

    /**
     * 搜索书籍
     */
    searchBooks(e) {
        const keywords = e.detail.keywords
        this.setData({
            books: [],
            keywords: keywords,
            page: {
                pageNo: 1,
                pageSize: 10,
                totalCount: 0,
                totalPages: 0
            }
        })
        this.loadBooks(this.data.page.pageNo, this.data.page.pageSize, null, keywords)
    },

    /**
     * 加载书籍列表
     */
    async loadBooks(pageNo, pageSize, shareCode = null, keywords = null) {
        if (this.data.loadBooksLock) {
            return
        }
        try {
            this.data.loadBooksLock = true
            let finalPath = `api/shelf/listBook?pageNo=${pageNo}&pageSize=${pageSize}`
            if (shareCode) {
                finalPath += `&shareCode=${shareCode}`
            }
            if (keywords) {
                keywords = encodeURIComponent(keywords)
                finalPath += `&keyword=${keywords}`
            }
            const res = await invoke({
                path: finalPath
            })
            this.setData({
                books: this.data.books.concat(res.rows),
                page: {
                    pageNo: res.pageNo,
                    pageSize: res.pageSize,
                    totalCount: res.totalCount,
                    totalPages: res.totalPages
                }
            })
        } finally {
            this.data.loadBooksLock = false
        }
    },

    onLoad() {
        this.loadBooks(this.data.page.pageNo, this.data.page.pageSize)
    },

    /**
     * 滚动到底部时触发
     */
    onScrollToBottom() {
        if (this.data.page.pageNo === this.data.page.totalPages) {
            return
        }
        const nextPage = parseInt(this.data.page.pageNo) + 1
        this.loadBooks(nextPage, this.data.page.pageSize)
    }

})