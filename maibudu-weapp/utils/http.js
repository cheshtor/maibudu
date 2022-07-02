import {showError} from './common.js'

export default async function invoke(obj, number = 0) {
    try {
        if (!obj.method) {
            obj.method = 'GET'
        }
        const result = await wx.cloud.callContainer({
            ...obj,
            header: {
                'X-WX-SERVICE': 'springboot-qzpg'
            }
        })
        const resp = result.data
        if (resp.success) {
            return resp.data
        } else {
            showError(resp.status ? '系统开小差了，请再试试！' : resp.message)
            return
        }
    } catch (e) {
        showError('系统出现了一些小问题，不要慌！')
        return
    }
}