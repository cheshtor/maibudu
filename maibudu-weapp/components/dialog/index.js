Component({
    options: {
        multipleSlots: true
    },
    properties: {
        show: {
            type: Boolean,
            value: false
        },
        title: {
            type: String,
            value: '确认吗？'
        },
        confirmBtnText: {
            type: String,
            value: '确认'
        }
    },

    data: {

    },

    methods: {
        onClose() {
            this.triggerEvent('onClose', {}, {})
        },
        onConfirm() {
            this.triggerEvent('onConfirm', {}, {})
        }
    }
})
