package com.cedis.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author jjq
 * @version 1.0
 * @date 2020/11/9
 * @desc kmp算法
 * @ref http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html
 * http://jakeboxer.com/blog/2009/12/13/the-knuth-morris-pratt-algorithm-in-my-own-words/
 */
public class KMP {


    @Test
    public void getPartialMatchTableTest() {
        int[] table = getPartialMatchTable("ABABABA");
        System.out.println(Arrays.toString(table));
    }


    /**
     * 获取next数组
     * ABABABABC
     * ABABABC
     *
     * @param pattern 模式串
     * @return
     */
    public static int[] getPartialMatchTable(String pattern) {
        int[] pmt = new int[pattern.length()];
        for (int i = 2; i <= pattern.length(); i++) {
            for (int j = 1; j < i; j++) {
                String a = pattern.substring(0, j);
                String b = pattern.substring(i - j, i);
                if (a.equals(b)) {
                    pmt[i - 1] = j;
                }
            }
        }
        return pmt;
    }

    /**
     * 获取next数组 (双指针)
     *
     * ABABABC
     * @param pattern 模式串
     * @return
     */
    public static int[] getPartialMatchTable2(String pattern) {
        int[] pmt = new int[pattern.length()];
        for (int i = 0; i <= pattern.length(); i++) {
            for (int j = 1; j <= pattern.length(); j++) {
                if (pattern.charAt(i) == pattern.charAt(j)) {
                    i++;
                    j++;
                }else {
                    j++;
                }
            }
        }
        return pmt;
    }

    /**
     * 0 1 2 3 4 5 6 7 8
     * A B A B A B A B A B C
     *         A B A B A B C          => [0, 0, 1, 2, 3, 4, 5]
     * @param pattern 匹配串
     * @param src     源串
     * @return
     */
    public static int KMP(String pattern, String src) {
        int[] pmt = getPartialMatchTable(pattern);
        int m=0;
        for (int i = 0; i < pattern.length(); i++) {
            if (i+m >= src.length()) {
                return -1;
            }
            char pc = pattern.charAt(i);
            char sc = src.charAt(i + m);
            if (pc != sc) {
                m += i-pmt[i-1];
                i=-1;
                if (m + pattern.length() > src.length()) {
                    return -1;
                }
            }
        }
        return m;
    }

    /**
     * ABABABABAABBC
     *       ABAABD
     * 001120
     */
    @Test
    public void KMPTest() {
        //int kmp = KMP("ABABABC", "ABABABABABC");
        int kmp = KMP("ABAABD", "ABABABABAABBC");
        System.out.println("KMP:" + kmp);
    }



}
