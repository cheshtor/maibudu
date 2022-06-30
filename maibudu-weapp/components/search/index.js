Component({
    properties: {
        placeholder: {
            type: String,
            value: '打开新世界的大门'
        },
        leftIcon: {
            type: String,
            value: 'share-o'
        },
        text: {
            type: String,
            value: '搜索'
        },
        clean: {
            type: Boolean,
            value: false
        }
    },

    data: {
        keyword: ''
    },

    methods: {
        onSearch() {
            this.triggerEvent("onSearch", {keyword: this.data.keyword}, {})
            if (this.properties.clean) {
                this.setData({
                    keyword: ''
                })
            }
        }
    }
})
