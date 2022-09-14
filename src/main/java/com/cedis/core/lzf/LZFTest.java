package com.cedis.core.lzf;

import com.ning.compress.lzf.LZFCompressingInputStream;
import org.apache.commons.codec.binary.Hex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/12
 * @desc
 */
public class LZFTest {

    public void compress(String filePath) throws IOException {
        //String filePath = "classpath:lzfcompress.txt";
        //ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //InputStream input = classLoader.getResourceAsStream("src/main/resources/lzfcompress.txt");
        InputStream in = new LZFCompressingInputStream(new FileInputStream(filePath));
        byte[] read = new byte[64];
        if (in.read(read) > 0 ) {
            System.out.println("result:" + Hex.encodeHexString(read));
            //5a56012854533510312e20496e
        }


    }


}
