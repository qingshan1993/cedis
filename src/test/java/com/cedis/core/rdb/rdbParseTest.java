package com.cedis.core.rdb;

import com.cedis.core.old.ParseRDB;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
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
    public void rdbParse() {
        final AtomicInteger count = new AtomicInteger();
        String filePath = "D:\\dump.rdb";

        ParseRDB rdb = new ParseRDB();
        rdb.init(new File(filePath));
        ParseRDB.Entry entry = rdb.next();

        while(entry!=null){
            count.incrementAndGet();
            entry = rdb.next();
        }
        rdb.close();
    }

    @Test
    public void rdbReadTest() throws Exception {
        String filePath = "D:\\dump.rdb";
        //Parser parser = new Parser();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        FileChannel channel = new RandomAccessFile(filePath,"r").getChannel();
        //byteBuffer.limit(2);
        int read = channel.read(byteBuffer);

        if (read != -1) {
            byte[] result = new byte[5];
            byte[] array = byteBuffer.get(result, 0, 5).array();
            System.out.println("result:" + new String(array));
            byte[] result1 = new byte[8];
            byteBuffer.get(result1, 0, 8);
            System.out.println("result1:" + new String(result1));
        }

//        while (channel.read(allocate) != -1) {
//            long position = channel.position();
//            allocate.limit()
//            allocate.clear();
//            byte[] array = allocate.array();
//            System.out.println("result:" + new String(array));
//            System.out.println("position:" + position);
//        }
    }
}
