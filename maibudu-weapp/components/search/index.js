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
        }
    },

    data: {
        keyword: ''
    },

    methods: {
        onSearch() {
            this.triggerEvent("onSearch", {keyword: this.data.keyword}, {})
        }
    }
})
