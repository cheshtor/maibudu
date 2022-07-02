import Notify from '@vant/weapp/notify/notify'
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

const showNotify = (message, selector = '#van-notify') => {
    Notify({
        message: message,
        background: '#93d8f8',
        color: '#2f2d51',
        selector: selector
    })
}

const showError = (message, selector = '#van-notify') => {
    Notify({
        message: message,
        background: '#ff97b5',
        color: '#2f2d51',
        selector: selector
    })
}

module.exports = {
    gotoPage: gotoPage,
    showNotify: showNotify,
    showError: showError
}