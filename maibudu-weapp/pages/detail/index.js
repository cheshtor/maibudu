import invoke from '../../utils/http'
Page({

    data: {

    },

    async onLoad(options) {
        const book = await invoke({
            path: `/api/book/get?bookId=${options.bookId}`
        })
        console.log(book)
    }
})