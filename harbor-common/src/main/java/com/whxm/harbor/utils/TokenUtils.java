package com.whxm.harbor.utils;

import java.util.UUID;

public class TokenUtils {
    /**
     * 全局默认分割点
     */
    public final static int FACTOR = 37;

    /**
     * 扰乱
     *
     * @param text   32位字符串
     * @param factor 分割点
     * @return 64位字符串
     */
    public static String chaos(String text, String uuid, int factor) {
        String s = uuid + text;
        return s.substring(factor) + s.substring(0, factor);
    }

    /**
     * 秩序
     *
     * @param text   64位字符串
     * @param factor 分割点
     * @return 32位字符串
     */
    public static String order(String text, int factor) {
        text = text.substring(64 - factor) + text.substring(0, 64 - factor);
        return text.substring(text.length() / 2);
    }

    /**
     * 获取盐
     */
    public static String salt(String text, int factor) {
        text = text.substring(64 - factor) + text.substring(0, 64 - factor);
        return text.substring(0, text.length() / 2);
    }
}
