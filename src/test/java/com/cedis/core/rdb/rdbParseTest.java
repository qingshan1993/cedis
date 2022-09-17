package com.cedis.core.rdb;

import com.cedis.constant.RDBConstant;
import com.cedis.core.rdb.parser.DefaultParser;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

/**
 * @author jjq
 * @version 1.0
 * @date 2020/8/16
 * @desc rdb 文件解析测试
 * http://rdb.fnordig.de/file_format.html
 */
public class rdbParseTest {



    @Test
    public void rdbParse() throws IOException {

        String filePath = "D:\\dump.rdb";
        FileChannel fileChannel = new RandomAccessFile(filePath, "r").getChannel();
        ByteBuffer allocate = ByteBuffer.allocate(1024);

        fileChannel.read(allocate);
        CharBuffer charBuffer1 = allocate.asCharBuffer().asReadOnlyBuffer();
        char[] array = charBuffer1.array();
        System.out.println(charBuffer1);
        fileChannel.close();

    }

    @Test
    public void rdbReadTest() throws Exception {
        String filePath = "D:\\dump.rdb";
        DefaultParser parser = new DefaultParser(filePath, 1024);
        parser.read();
        parser.verifyStringEqual(5, "REDIS");
        parser.verifyStringEqual(4, "0008");


        parser.close();
    }

    @Test
    public void rdbReadTest1() throws Exception {
        char xx = '\0';

        boolean result= RDBConstant.RDB_OPCODE_AUX == (11);
        //-5 & 0xff
        System.out.println(result);
    }
}
