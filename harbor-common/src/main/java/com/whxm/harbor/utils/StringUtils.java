package com.whxm.harbor.utils;



import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.Random;

import java.util.UUID;


public class StringUtils {

    private static SimpleDateFormat fileSdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    private static SimpleDateFormat dirSdf = new SimpleDateFormat("yyyy-MM-dd");

    private static SimpleDateFormat roundSdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

    private static Random ran = new Random();



    public static String createStrUseTime(String originName) {

        String newName = "";

        String regex = "^.+(\\..+)$";

        String end = originName.replaceAll(regex, "$1");

        String first = fileSdf.format(new Date());

        int twice = ran.nextInt(9000) + 1000;

        return (newName = first + twice + end);

    }



    /**

     * 使用UUID生成字符串

     * UUID原则上在同一个电脑上面产生的32位的字符串不是不会重复的

     *

     * @param oldName

     */

    public static String createStrUseUUID(String oldName) {

        String newName = "";

        //得到文件的后缀名---[String end=oldName.substring(oldName.lastIndexOf("."))]

        String regex = "^.+(\\..+)$";

        String end = oldName.replaceAll(regex, "$1");

        //生成UUID

        String start = UUID.randomUUID().toString().replace("-", "").toUpperCase();

        newName = start + end;

        return newName;

    }



    public static String createDirName() {

        String time = dirSdf.format(new Date());

        //System.out.println(time);

        return time;

    }



    public static void main(String[] args) {

        System.out.println(createStrUseTime("xxx...aaa...jpg"));

    }



    public static String createRoundId(String prefix) {

        return prefix+roundSdf.format(new Date()) + "_" + (ran.nextInt(9000) + 1000) + 1000;

    }

}