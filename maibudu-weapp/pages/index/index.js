import invoke from '../../utils/http'

Page({
  data: {
    info: 'hello'
  },

  scanBook() {
      wx.scanCode({
        onlyFromCamera: false,
        scanType: ['barCode'],
        success: async function(res) {
            const response = await invoke({
                path: '/api/book/scan?isbn=' + res.result
            })
            if (response.success) {
                wx.showToast({
                    title: response.data.title,
                    icon: 'success',
                    duration: 1200
                  })
            } else {
                wx.showToast({
                    title: response.message,
                    icon: 'error',
                    duration: 1200
                  })
            }
        },
        fail: function() {

        }
      })
  }
  
})
