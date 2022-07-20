package com.mabushizai.maibudu.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mabushizai.maibudu.config.MaibuduException;
import com.mabushizai.maibudu.dto.JikeBookInfo;
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
}
