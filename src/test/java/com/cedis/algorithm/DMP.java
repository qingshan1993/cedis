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
    public static int[] getPartialMatchTable(String pattern) {
        int[] pmt = new int[pattern.length()];
        for (int i=2; i<=pattern.length(); i++) {
            for (int j=1;j<i; j++) {
                String a = pattern.substring(0, j);
                String b = pattern.substring(i-j, i);
                if (a.equals(b)) {
                    pmt[i-1] = j;
                }
            }
        }
        return pmt;
    }
    
 }
