package com.mabushizai.maibudu.utils;

import org.springframework.util.StringUtils;

/**
 * 用户 ID 上下文
 *
 * @author Pengyu Gan
 * CreateDate 2022/6/21
 */
public class UserContext {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private UserContext() {

    }

    public static void setUid(String uid) {
        threadLocal.set(uid);
    }

    public static String getUid() {
        String uid = threadLocal.get();
        if (!StringUtils.hasLength(uid)) {
            throw new RuntimeException("非法用户访问");
        }
        return uid;
    }

    public static void removeUid() {
        threadLocal.remove();
    }

}
