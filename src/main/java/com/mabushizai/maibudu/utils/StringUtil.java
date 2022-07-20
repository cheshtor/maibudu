package com.mabushizai.maibudu.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Pengyu Gan
 * CreateDate 2022/6/21
 */
public class StringUtil {

    private static final Pattern chinesePattern = Pattern.compile("[一-龥]");

    private static final Pattern lettersPattern = Pattern.compile("[a-zA-Z\\s]");

    private static final Random random = new Random();

    private static final String chars = "uo8KTOnrb6iHQvZXLMFq72mEe1ca0hjzU5yxCtD9pYR4NSdAlGkwBgVPIW3fJs";

    public static String removeChinese(String source) {
        Matcher matcher = chinesePattern.matcher(source);
        return matcher.replaceAll("");
    }

    public static String removeLetters(String source) {
        Matcher matcher = lettersPattern.matcher(source);
        return matcher.replaceAll("");
    }

    public static String cleanPrice(String price) {
        price = removeChinese(price);
        return removeLetters(price);
    }

    public static String generateCode() {
        StringBuilder buffer = new StringBuilder();
        char[] cs = chars.toCharArray();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(62);
            buffer.append(cs[index]);
        }
        return buffer.toString();
    }

}
