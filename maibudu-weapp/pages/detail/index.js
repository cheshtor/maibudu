import invoke from '../../utils/http'

Page({

    data: {
        bookId: -1,
        cover: '',
        title: '',
        subtitle: '',
        author: '',
        publisher: '',
        publishDate: '',
        isbn: '',
        mine: true,
        ownerNickname: '',
        ownerAvatar: ''
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
            publishDate: book.publishDate,
            isbn: book.isbn,
            mine: book.mine,
            ownerNickname: book.ownerNickname,
            ownerAvatar: book.ownerAvatar
        })
    }
})