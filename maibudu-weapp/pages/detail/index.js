import invoke from '../../utils/http'

Page({

    data: {
        bookId: -1,
        cover: '',
        title: '',
        subtitle: '',
        author: '',
        publisher: '',
        publishDate: ''
    },

    async onLoad(options) {
        const bookId = options.bookId
        const book = await invoke({
            path: `/api/book/get?bookId=${bookId}`
        })
        this.setData({
            bookId: bookId,
            cover: book.cover,
            title: book.title,
            subtitle: book.subtitle,
            author: book.author,
            publisher: book.publisher,
            publishDate: book.publishDate
        })
    }
})