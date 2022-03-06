package com.cedis.core.rdb;

import com.cedis.constant.RDBConstant;
import com.cedis.core.old.ParseRDB;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jjq
 * @version 1.0
 * @date 2020/8/16
 * @desc rdb 文件解析测试
 */
public class rdbParseTest {



    @Test
    public void rdbParse() throws FileNotFoundException {

        String filePath = "D:\\dump.rdb";
        FileChannel fileChannel = new RandomAccessFile(filePath, "r").getChannel();
        IntBuffer intBuffer = IntBuffer.allocate(1024);
        //fileChannel.read(intBuffer);

    }

    @Test
    public void rdbReadTest() throws Exception {
        String filePath = "D:\\dump.rdb";
        Parser parser = new Parser(filePath, 1024);
        parser.read();
        parser.verifyStringEqual(5, "REDIS");
        parser.verifyStringEqual(4, "0008");


        parser.close();
    }

    @Test
    public void rdbReadTest1() throws Exception {

        boolean result= RDBConstant.RDB_OPCODE_AUX == (11);
        //-5 & 0xff
        System.out.println(result);
    }
}
