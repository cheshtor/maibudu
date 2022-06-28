package com.mabushizai.maibudu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Pengyu Gan
 * CreateDate 2022/6/21
 */
public class StringUtil {

    private static final Pattern chinesePattern = Pattern.compile("[一-龥]");

    public static String removeChinese(String source) {
        Matcher matcher = chinesePattern.matcher(source);
        return matcher.replaceAll("");
    }

}
