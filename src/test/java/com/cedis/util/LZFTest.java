package com.cedis.util;

import com.ning.compress.lzf.LZFCompressingInputStream;
import com.ning.compress.lzf.LZFInputStream;
import com.ning.compress.lzf.LZFOutputStream;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/11
 * @desc
 */
public class LZFTest {

    /**
     * lzf压缩测试
     * https://github.com/ning/compress
     */
    @Test
    public void lzfTest() throws IOException {
        String filePath = "D:\\lzftest.txt";
        InputStream in = new LZFCompressingInputStream(new FileInputStream(filePath));
        byte[] read = new byte[1024];
//        if (in.read(read) > 0 ) {
//            System.out.println("result:" + Hex.encodeHexString(read));
//            //5a56012854533510312e20496e
//        }

        int tmp = 0;
        int total = 0;
        while ((tmp = in.read(read)) > 0 ) {
            total = tmp + total;
            //10331
        }
        System.out.println("result:" +total);
    }
}
