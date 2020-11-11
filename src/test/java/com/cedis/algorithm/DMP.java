package com.cedis.algorithm;

import org.junit.jupiter.api.Test;

/**
 * @author jjq
 * @version 1.0
 * @date 2020/11/9
 * @desc dmp算法
 * @ref http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html
 *      http://jakeboxer.com/blog/2009/12/13/the-knuth-morris-pratt-algorithm-in-my-own-words/
 *
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

     /**
     *
     * @param pattern 匹配串
     * @param src 源串
     * @return
     */
    public static int KMP(String pattern, String src) {
        int[] partialMatchTable = getPartialMatchTable(pattern);
        for (int i=0; i< src.length()-pattern.length(); i++) {
            for (int j=0; j<pattern.length(); j++) {
                if (pattern.charAt(j) != src.charAt(i)) {
                    //

                }
            }
        }
        return 1;
    }

    
 }
