import invoke from './utils/http.js'

App({
  async onLaunch() {
    wx.cloud.init({
        env: 'prod-3ggnrc2pfd5a1785',
        traceUser: true,
    })
    this.loadShareCode()
  },

  async loadShareCode() {
    const shareCode = await invoke({
        path: '/api/user/shareCode'
    })
    wx.setStorageSync('shareCode', shareCode)
  }
})
