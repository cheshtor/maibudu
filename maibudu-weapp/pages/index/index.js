import invoke from '../../utils/http'
import {gotoPage} from '../../utils/common'


Page({
  data: {
  },

  /**
   * 跳转我的书架页
   */
  goBookshelf() {
    gotoPage('../bookshelf/index')
  },

  /**
   * 导入共享书架
   */
  importShare(e) {
    const shareCode = e.detail.keyword
    if (!shareCode) {
        return;
    }
    // TODO
    console.log(shareCode)
    
  },

  /**
   * 调起相机扫码
   */
  scanBook() {
      wx.scanCode({
        onlyFromCamera: false, // 允许从相册选择照片
        scanType: ['barCode'], // 只允许扫条形码，不能扫二维码
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
