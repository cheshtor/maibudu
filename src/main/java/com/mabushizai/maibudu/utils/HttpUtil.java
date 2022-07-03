package com.mabushizai.maibudu.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mabushizai.maibudu.config.MaibuduException;
import com.mabushizai.maibudu.dto.JikeBookInfo;
import com.mabushizai.maibudu.dto.JvHeBookInfo;
import com.mabushizai.maibudu.dto.TempAuth;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.util.Map;

/**
 * @author Pengyu Gan
 * CreateDate 2022/6/21
 */
@Slf4j
public class HttpUtil {

    private static final OkHttpClient httpClient = new OkHttpClient();

    private static final String JVHE_ISBN_URL_TEMPLATE = "http://feedback.api.juhe.cn/ISBN?key=%s&sub=%s";

    private static final String JIKE_ISBN_URL_TEMPLATE = "https://api.jike.xyz/situ/book/isbn/%s?apikey=%s";

    public static final MediaType MEDIA_TYPE_JSON = MediaType.get("application/json; charset=utf-8");

    public static String post(String url, Map<String, String> headers, String requestBody) {
        Request.Builder builder = new Request.Builder().url(url);
        if (null != headers && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                builder = builder.header(entry.getKey(), entry.getValue());
            }
        }
        builder.post(RequestBody.create(requestBody, MEDIA_TYPE_JSON));
        return doRequest(builder.build(), url);
    }

    public static String get(String url, Map<String, String> headers) {
        Request.Builder builder = new Request.Builder().url(url);
        if (null != headers && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                builder = builder.header(entry.getKey(), entry.getValue());
            }
        }
        return doRequest(builder.build(), url);
    }

    public static String doRequest(Request request, String url) {
        try (Response response = httpClient.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (null != responseBody) {
                return responseBody.string();
            }
            log.error("GET {} 请求未返回数据。", url);
            throw new MaibuduException(String.format("GET %s 请求未返回数据", url));
        } catch (Throwable e) {
            log.error("GET {} 请求失败。", url, e);
            throw new MaibuduException(String.format("GET %s 请求失败。", url));
        }
    }

    public static String[] getFileMetadata(String requestBody) {
        String url = "https://api.weixin.qq.com/_/cos/metaid/encode";
        String responseBody = post(url, null, requestBody);
        try {
            JSONObject jsonObject = JSON.parseObject(responseBody);
            Integer errorCode = jsonObject.getInteger("errcode");
            if (errorCode != 0) {
                log.error("获取文件元数据失败。请求体：{}，errorCode：{}，errmsg：{}", requestBody, errorCode, jsonObject.getString("errmsg"));
                return null;
            }
            JSONObject respdata = jsonObject.getJSONObject("respdata");
            return respdata.getJSONArray("x_cos_meta_field_strs").toArray(new String[0]);
        } catch (Throwable e) {
            log.error("获取文件元数据异常", e);
            return null;
        }
    }

    public static TempAuth getCOSTempAuth() {
        String url = "http://api.weixin.qq.com/_/cos/getauth";
        String responseBody = get(url, null);
        try {
            return JSON.parseObject(responseBody, TempAuth.class);
        } catch (Throwable e) {
            log.error("获取 COS 临时秘钥失败。", e);
            return null;
        }
    }

    public static JikeBookInfo getJikeBookInfo(String isbn) {
        String apiKey = "12893.64dfcdfc527daf6f3c5ee64517ea3a10.757769ee75ec159312e5da15eec0264d";
        String url = String.format(JIKE_ISBN_URL_TEMPLATE, isbn, apiKey);
        String responseBody = get(url, null);
        JSONObject jsonObject = JSON.parseObject(responseBody);
        Object data = jsonObject.get("data");
        if (null == data) {
            log.error("[极客]获取图书信息失败。ISBN：{}", isbn);
            return null;
        }
        return JSON.parseObject(jsonObject.getString("data"), JikeBookInfo.class);
    }

    public static JvHeBookInfo getJvHeBookInfo(String isbn) {
        String key = "5dd74dbd82c759b2df5ea041569ef54b";
        String url = String.format(JVHE_ISBN_URL_TEMPLATE, key, isbn);
        String responseBody = get(url, null);
        JSONObject jsonObject = JSON.parseObject(responseBody);
        Integer errorCode = jsonObject.getInteger("error_code");
        if (errorCode != 0) {
            if (errorCode == 204402) {
                return null;
            }
            String errorMessage = jsonObject.getString("reason");
            log.error("[聚合]获取图书信息失败。第三方错误码：{}，第三方错误信息：{}, ISBN：{}", errorCode, errorMessage, isbn);
            throw new MaibuduException(String.format("获取图书信息失败。错误码：%s", errorCode));
        }
        return JSON.parseObject(jsonObject.getString("result"), JvHeBookInfo.class);
    }

}
