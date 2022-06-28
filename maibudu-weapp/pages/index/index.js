import invoke from '../../utils/http'
import Dialog from '../../miniprogram_npm/@vant/weapp/dialog/dialog'

Page({
  data: {
    info: 'hello'
  },

  scanBarCode() {
      wx.scanCode({
        onlyFromCamera: false,
        scanType: ['barCode'],
        success: async function(res) {
            const bookInfo = await invoke({
                path: '/api/book/scan?isbn=' + res.result
            })
            Dialog.alert({
                title: '请确认书籍信息',
                message: bookInfo.name + "(" + bookInfo.author + ")",
              }).then(() => {
                
              });
        },
        fail: function() {

        }
      })
  }
  
})
