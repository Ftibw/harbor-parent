package com.whxm.harbor.utils;

public class TokenUtils {
    /*
     * 搅拌比例
     */
    private final static int FACTOR = 37;

    /**
     * 加盐搅拌5次
     *
     * @param text 32位字符串
     * @return 64位字符串
     */
    public static String chaos(String text, String salt) {
        String s = salt + text;
        s = s.substring(FACTOR) + s.substring(0, FACTOR);
        s = s.substring(FACTOR) + s.substring(0, FACTOR);
        s = s.substring(FACTOR) + s.substring(0, FACTOR);
        s = s.substring(FACTOR) + s.substring(0, FACTOR);
        s = s.substring(FACTOR) + s.substring(0, FACTOR);
        return s;
    }

    /**
     * 分离去盐获得原始字符串
     *
     * @param text 64位字符串
     * @return 32位字符串
     */
    public static String order(String text) {
        text = text.substring(64 - FACTOR) + text.substring(0, 64 - FACTOR);
        text = text.substring(64 - FACTOR) + text.substring(0, 64 - FACTOR);
        text = text.substring(64 - FACTOR) + text.substring(0, 64 - FACTOR);
        text = text.substring(64 - FACTOR) + text.substring(0, 64 - FACTOR);
        text = text.substring(64 - FACTOR) + text.substring(0, 64 - FACTOR);
        return text.substring(text.length() / 2);
    }

    /**
     * 分离盐获取原始盐
     */
    public static String salt(String text) {
        text = text.substring(64 - FACTOR) + text.substring(0, 64 - FACTOR);
        text = text.substring(64 - FACTOR) + text.substring(0, 64 - FACTOR);
        text = text.substring(64 - FACTOR) + text.substring(0, 64 - FACTOR);
        text = text.substring(64 - FACTOR) + text.substring(0, 64 - FACTOR);
        text = text.substring(64 - FACTOR) + text.substring(0, 64 - FACTOR);
        return text.substring(0, text.length() / 2);
    }
}
