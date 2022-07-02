import invoke from './utils/http.js'

App({

    globalData: {
        isRegistered: false
    },

    async onLaunch() {
        wx.cloud.init({
            env: 'prod-3ggnrc2pfd5a1785',
            traceUser: true,
        })
        this.loadUser()
    },

    /**
     * 加载用户信息
     */
    async loadUser() {
        wx.removeStorageSync('shareCode')
        wx.removeStorageSync('nickname')
        wx.removeStorageSync('avatar')
        wx.removeStorageSync('uid')
        const user = await invoke({
            path: '/api/user/get'
        })
        if (user) {
            wx.setStorageSync('shareCode', user.code)
            wx.setStorageSync('nickname', user.nickname)
            wx.setStorageSync('avatar', user.avatar)
            wx.setStorageSync('uid', user.uid)
            this.globalData.isRegistered = true
        } else {
            const openid = await invoke({
                path: '/api/user/openid'
            })
            if (openid) {
                wx.setStorageSync('uid', openid)
            }
        }
    }
})