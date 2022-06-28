App({
  onLaunch() {
    wx.cloud.init({
        env: 'prod-3ggnrc2pfd5a1785',
        traceUser: true,
    })
  }
})
