package org.example;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author cleanwk
 * @date 2022/7/9
 */
public class RandomStringUtilsLang3 {
    public static void main(String[] args) {
        //随机生成n位数数字
        RandomStringUtils.randomNumeric(5);
        //在指定字符串中生成长度为n的随机字符串
        RandomStringUtils.random(5, "abcdefghijk");
        //指定从字符或数字中生成随机字符串
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomStringUtils.random(5, true, false));
            System.out.println(RandomStringUtils.random(5, true, true));
            System.out.println("\n");
        }

    }
}
