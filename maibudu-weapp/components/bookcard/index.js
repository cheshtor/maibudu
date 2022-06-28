import invoke from "../../utils/http"

Component({
    properties: {
        bookId: Number
    },

    data: {
        cover: '',
        name: '',
        author: ''
    },

    lifetimes: {
        async attached() {
            const res = await invoke({
                path: "/api/book/get?bookId=" + this.properties.bookId
            })
            this.setData({
                cover: res.cover,
                name: res.name,
                author: res.author
            })
        }
    }
})
