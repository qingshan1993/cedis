package com.cedis.core.rdb;

import java.io.ByteArrayInputStream;

/**
 * @author jjq
 * @version 1.0
 * @date 2022/3/4
 * @desc rdb文件解析器
 */
public class Parser {

    private ByteArrayInputStream rdb;


    /**
     * 验证rdb文件某一段byte数组是否和expect字符串相等
     * @param start byte数组的开始位置
     * @param length byte数组的长度
     * @param expect 期望相等的字符春
     * 如果不相等将抛出异常信息
     */
    private void verifyStringEqual(int start, int length, String expect) {


    }
}
