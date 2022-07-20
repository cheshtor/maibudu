package com.mabushizai.maibudu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mabushizai.maibudu.dto.JikeBookInfo;
import com.mabushizai.maibudu.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 书籍信息抓取服务
 *
 * @author Pengyu Gan
 * CreateDate 2022/7/20
 */
@Slf4j
@Service
public class BookFetchService {

    private static final String JIKE_ISBN_URL_TEMPLATE = "https://api.jike.xyz/situ/book/isbn/%s?apikey=%s";

    private static final List<String> JIKE_API_KEY_LIST = new ArrayList<>();

    private static int apiKeySelectedIndex = 0;

    @PostConstruct
    public void init() {
        JIKE_API_KEY_LIST.add("12893.64dfcdfc527daf6f3c5ee64517ea3a10.757769ee75ec159312e5da15eec0264d");
    }

    public JikeBookInfo fetchFromJike(String isbn) {
        try {
            String apiKey = getJikeApiKey();
            String url = String.format(JIKE_ISBN_URL_TEMPLATE, isbn, apiKey);
            String responseBody = HttpUtil.get(url, null);
            JSONObject jsonObject = JSON.parseObject(responseBody);
            Object data = jsonObject.get("data");
            if (null == data) {
                log.error("[极客]获取图书信息失败。ISBN：{}", isbn);
                return null;
            }
            return JSON.parseObject(jsonObject.getString("data"), JikeBookInfo.class);
        } catch (Throwable e) {
            apiKeySelectedIndex++;
            if (apiKeySelectedIndex < JIKE_API_KEY_LIST.size()) {
                log.error("[极客]获取图书信息出现异常，切换 APIKEY 重新尝试。");
                fetchFromJike(isbn);
            }
        }
        log.error("[极客]尝试了所有 APIKEY 仍然无法获取到图书信息。");
        return null;
    }

    private String getJikeApiKey() {
        if (apiKeySelectedIndex >= JIKE_API_KEY_LIST.size()) {
            apiKeySelectedIndex = 0;
        }
        return JIKE_API_KEY_LIST.get(apiKeySelectedIndex);
    }

}
