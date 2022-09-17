package com.cedis.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/14
 * @desc 断言工具类
 */
public class AssertUtils {

    /**
     * assert two strings whether equals, otherwise throw RuntimeException
     * 断言两个字符串是否相等, 不相等则抛出异常
     * @param str1
     * @param str2
     * @param rex
     */
    public static void strEquals(String str1, String str2, RuntimeException rex) {
        if (!StringUtils.equals(str1, str2)) {
            throw rex;
        }
    }

    /**
     * assert two strings whether equals, otherwise throw RuntimeException
     * 断言两个字符串是否相等, 不相等则抛出异常
     * @param str1
     * @param str2
     */
    public static void strEquals(String str1, String str2) {
        strEquals(str1, str2, new IllegalArgumentException(str1 + " not equals " + str2));
    }

    /**
     * assert two object whether equals, otherwise throw RuntimeException
     * 断言两个实例是否相等, 不相等则抛出异常
     * @param obj1
     * @param obj2
     * @param rex
     */
    public static void objEquals(Object obj1, Object obj2, RuntimeException rex) {
        if (!Objects.equals(obj1, obj2)) {
            if (rex == null) {
                throw new IllegalArgumentException(obj1 + " not equals " + obj2);
            } else {
                throw rex;
            }
        }
    }



}