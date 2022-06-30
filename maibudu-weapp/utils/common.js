/**
 * 页面跳转
 */
const gotoPage = (url, params) => {
    let querystring = ''
    if (params) {
        for (let key in params) {
            querystring += `${key}=${params[key]}&`
        }
    }
    if (querystring) {
        querystring = querystring.substr(0, querystring.length - 1)
        url = `${url}?${querystring}`
    }
    wx.navigateTo({
        url: url,
    })
}

const showNotify = (message) => {
    wx.showToast({
        title: message,
        icon: 'none',
        duration: 2000
    })
}

module.exports = {
    gotoPage: gotoPage,
    showNotify: showNotify
}