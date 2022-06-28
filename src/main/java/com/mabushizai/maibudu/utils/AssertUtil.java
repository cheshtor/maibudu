package com.mabushizai.maibudu.utils;

import org.springframework.util.StringUtils;

/**
 * 断言工具
 *
 * @author Gan Pengyu
 * CreateDate 2022/6/20
 */
public class AssertUtil {

    public static void isTrue(boolean condition, String errorMsg) {
        if (!condition) {
            throw new RuntimeException(errorMsg);
        }
    }

    public static void notNull(Object obj, String errorMsg) {
        if (null == obj) {
            throw new RuntimeException(errorMsg);
        }
    }

    public static void notEmpty(CharSequence str, String errorMsg) {
        if (!StringUtils.hasLength(str)) {
            throw new RuntimeException(errorMsg);
        }
    }

}
