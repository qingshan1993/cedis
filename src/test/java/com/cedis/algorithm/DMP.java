package com.cedis.algorithm;

import org.junit.jupiter.api.Test;

/**
 * @author jjq
 * @version 1.0
 * @date 2020/11/9
 * @desc dmp算法
 */
public class DMP {



    @Test
    public void getPartialMatchTableTest() {
        getPartialMatchTable("ABABABA");
    }


    /**
     *获取next数组
     *
     *
     * ABABABABC
     * ABABABC
     * @param pattern 模式串
     * @return
     */
    private static int[] getPartialMatchTable(String pattern) {
        int len = pattern.length();
        for (int j=1;j<len; j++) {
            String a = pattern.substring(0, j);
            String b = pattern.substring(len-j, len);
            if (a.equals(b)) {
                System.out.println(j);
            }


        }






        return null;
    }
 }
