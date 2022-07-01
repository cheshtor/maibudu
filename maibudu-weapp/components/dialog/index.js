Component({
    options: {
        multipleSlots: true
    },
    properties: {
        show: {
            type: Boolean,
            value: false
        },
        title: String,
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
