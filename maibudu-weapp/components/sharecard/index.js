import invoke from '../../utils/http'
Component({

    options: {
        styleIsolation: 'shared'
    },
    /**
     * 组件的属性列表
     */
    properties: {
        nickname: String,
        avatar: String,
        code: String
    },

    /**
     * 组件的初始数据
     */
    data: {

    },

    /**
     * 组件的方法列表
     */
    methods: {
        onClickShelf() {
            this.triggerEvent("onClickShelf", {shareCode: this.properties.code, nickname: this.properties.nickname}, {})
        },

        onRemoveShareShelf() {
            this.triggerEvent("onRemoveShareShelf", {shareCode: this.properties.code}, {})
        }
    }
})
