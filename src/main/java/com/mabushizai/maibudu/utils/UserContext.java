package com.mabushizai.maibudu.utils;

import com.mabushizai.maibudu.domain.User;
import org.springframework.util.StringUtils;

/**
 * 用户 ID 上下文
 *
 * @author Pengyu Gan
 * CreateDate 2022/6/21
 */
public class UserContext {

    private static final ThreadLocal<String> uidThreadLocal = new ThreadLocal<>();

    private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    private UserContext() {

    }

    public static void setUid(String uid) {
        uidThreadLocal.set(uid);
    }

    public static String getUid() {
        String uid = uidThreadLocal.get();
        if (!StringUtils.hasLength(uid)) {
            throw new RuntimeException("非法用户访问");
        }
        return uid;
    }

    public static void removeUid() {
        uidThreadLocal.remove();
    }

    public static void setUser(User user) {
        userThreadLocal.set(user);
    }

    public static String getCode() {
        User user = userThreadLocal.get();
        if (null == user) {
            return null;
        }
        return user.getCode();
    }

    public static void removeUser() {
        userThreadLocal.remove();
    }

    public static void remove() {
        removeUid();
        removeUser();
    }

    public static void initForTest() {
        uidThreadLocal.set("oso0q5VABTWlDGMSlCNuUSx6BOSA");
        User user = new User();
        user.setUid("oso0q5VABTWlDGMSlCNuUSx6BOSA");
        user.setNickname("青青草原抓羊小能手");
        user.setAvatar("https://7072-prod-3ggnrc2pfd5a1785-1312587653.tcb.qcloud.la/avatar/oso0q5VABTWlDGMSlCNuUSx6BOSA.jpeg");
        user.setCode("rZdEszbU");
        userThreadLocal.set(user);
    }

}
