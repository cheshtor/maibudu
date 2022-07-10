import {gotoPage} from '../../utils/common'
Component({
    options: {
        styleIsolation: 'shared'
    },
    properties: {
        title: {
            type: String,
            observer: function(nVal, oVal) {
                let t = nVal
                if (nVal.length > 13) {
                    t = nVal.substring(0, 13) + "..."
                }
                this.setData({
                    computedTitle: t
                })
            }
        },
        author: {
            type: String,
            observer: function(nVal, oVal) {
                let a = nVal
                if (nVal.length > 15) {
                     a = nVal.substring(0, 15) + "..."
                }
                this.setData({
                    computedAuthor: a
                })
            }
        },
        publisher: {
            type: String,
            observer: function(nVal, oVal) {
                let p = nVal
                if (nVal.length > 15) {
                    p = nVal.substring(0, 15) + "..."
                }
                this.setData({
                    computedPublisher: p
                })
            }
        },
        cover: String,
        bookId: String
    },

    data: {
        computedTitle: '',
        computedAuthor: '',
        computedPublisher: ''
    },

    methods: {
        showBookDetail() {
            gotoPage('../../pages/detail/index', {bookId: this.properties.bookId})
        }
    }
})
