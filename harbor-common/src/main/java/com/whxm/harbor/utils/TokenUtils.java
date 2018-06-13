package com.whxm.harbor.utils;

/**
 * @Author Ftibw
 * @Email ftibw@live.com
 * @CreateTime 2018/6/13 02:15
 */
public class TokenUtils {
    /**
     * 原料的搅拌比例
     */
    private final static int SOURCE_FACTOR = 19;
    /**
     * 混合后的搅拌高比例
     */
    private final static int TARGET_FACTOR_HIGH = 31;
    /**
     * 混合后的搅拌低比例
     */
    private final static int TARGET_FACTOR_LOW = 11;
    /**
     * 默认搅拌次数
     */
    private final static int DEFAULT_TIMES = 11;

    /**
     * 加盐搅拌默认次数
     *
     * @param text 32位字符串
     * @return 64位字符串
     */
    public static String chaos(String text, String salt) {

        for (int i = 0; i < DEFAULT_TIMES; i++) {
            text = text.substring(SOURCE_FACTOR) + text.substring(0, SOURCE_FACTOR);
        }

        String s = salt + text;

        for (int i = 0; i < DEFAULT_TIMES; i++) {

            s = s.substring(TARGET_FACTOR_HIGH) + s.substring(0, TARGET_FACTOR_HIGH);

            s = s.substring(TARGET_FACTOR_LOW) + s.substring(0, TARGET_FACTOR_LOW);
        }
        return s;
    }

    /**
     * 分离去盐获得原始字符串
     *
     * @param text 64位字符串
     * @return 32位字符串
     */
    public static String order(String text) {

        for (int i = 0; i < DEFAULT_TIMES; i++) {

            text = text.substring(64 - TARGET_FACTOR_HIGH) + text.substring(0, 64 - TARGET_FACTOR_HIGH);

            text = text.substring(64 - TARGET_FACTOR_LOW) + text.substring(0, 64 - TARGET_FACTOR_LOW);
        }

        text = text.substring(text.length() / 2);

        for (int i = 0; i < DEFAULT_TIMES; i++) {

            text = text.substring(32 - SOURCE_FACTOR) + text.substring(0, 32 - SOURCE_FACTOR);
        }

        return text;
    }

    /**
     * 分离盐获取原始盐
     */
    public static String salt(String text) {

        for (int i = 0; i < DEFAULT_TIMES; i++) {

            text = text.substring(64 - TARGET_FACTOR_HIGH) + text.substring(0, 64 - TARGET_FACTOR_HIGH);

            text = text.substring(64 - TARGET_FACTOR_LOW) + text.substring(0, 64 - TARGET_FACTOR_LOW);
        }

        return text.substring(0, text.length() / 2);
    }
}
