export default async function invoke(obj, number = 0) {
    try {
        const result = await wx.cloud.callContainer({
            path: obj.path,
            method: obj.method || 'GET',
            header: {
                'X-WX-SERVICE': 'springboot-qzpg'
            }
        })
        console.log(`微信云托管调用结果${result.errMsg} | callid:${result.callID}`)
        return result.data
    } catch (e) {
        const error = e.toString()
        if (error.indexOf("Cloud API isn't enabled") != -1 && number < 3) {
            return new Promise((resolve) => {
                setTimeout(function () {
                    resolve(that.call(obj, number + 1))
                }, 300)
            })
        } else {
            throw new Error(`微信云托管调用失败${error}`)
        }
    }
}