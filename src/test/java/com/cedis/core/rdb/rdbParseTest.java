package com.cedis.core.rdb;

import com.cedis.constant.RdbConstant;
import com.cedis.core.rdb.parser.DefaultParser;
import com.cedis.core.rdb.parser.ParseDispatcher;
import com.cedis.core.rdb.result.Entry;
import com.cedis.core.rdb.result.RdbResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author jjq
 * @version 1.0
 * @date 2020/8/16
 * @desc rdb 文件解析测试
 * http://rdb.fnordig.de/file_format.html
 */
public class rdbParseTest {



    @Test
    public void testRdbParse() throws IOException {
        RdbResult rdbResult = new ParserBuilder().fileSource("D:\\dump.rdb").build().readComplete();
        List<Entry> result = rdbResult.getResult();

    }

    @Test
    public void testInstantiateClass() throws Exception {
        Class<?> aClass = Class.forName("com.cedis.core.rdb.parser.Rdb8Parser", false, ParseDispatcher.class.getClassLoader());
        Object o = BeanUtils.instantiateClass(aClass);
        Package aPackage = ParseDispatcher.class.getPackage();
        System.out.println(":::::::::"+ aPackage.getName());
    }


    @Test
    public void testBuffer() throws IOException {
        String filePath = "D:\\lzf-str-dump.rdb";
        FileChannel fileChannel = new RandomAccessFile(filePath, "r").getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);
        fileChannel.read(byteBuffer);
        byteBuffer.flip();

        byte[] redisStr = new byte[5];
        byteBuffer.get(redisStr, 0, 5);
        System.out.println("magic:" +new String(redisStr, StandardCharsets.US_ASCII));

        //这个offset是针对目标数组的，而非针对buff的，它表示的含义是你想要把读取的结果放到redisStr2的偏移位置
        byte[] redisStr2 = new byte[4];
        byteBuffer.get(redisStr2, 0, 4);
        System.out.println("redis:" +new String(redisStr2, StandardCharsets.US_ASCII));

        byteBuffer.position(byteBuffer.position()-4);
        byte[] redisStr3 = new byte[4];
        byteBuffer.get(redisStr3, 0, 4);
        System.out.println("redis:" +new String(redisStr3, StandardCharsets.US_ASCII));
        fileChannel.close();
    }

    /**
     * 整形数字的存储：
     * 1.redis-bits：64,  c0 40 => 1100 0000 0100 0000
     *
     * 2.longkey: 1264758697098456
     *      10 31 32 36 34 37 35 38 36 39 37 30 39 38 34 35 36 =>0x10:16
     *
     * 3.ngint: -40,   c0 d8 => 1100000011011000
     *
     * 4.ctime: 1663512337(大约)
     * c2 a1 2e 27 63 => a1 2e 27 63: 2,704,156,515
     *
     * 5.smallkey :16400
     *  c1 10 40
     *
     * @throws Exception
     */
    @Test
    public void ngNumStrTest() throws Exception {
        //set ngint '-40'
        //00 05 6e 67 69 6e 74 c0 d8
        byte dd = (byte)0xd8;

        System.out.println("result:"+ dd);
        byte[] xix = new byte[1];
        xix[0] = dd;
        byte[] bytes = String.valueOf(xix).getBytes(StandardCharsets.US_ASCII);
        System.out.println(new String(bytes, StandardCharsets.US_ASCII));
        //C2 1D A6 26 63
    }

    /**
     * 整形存储
     *     C2        1D        A6        26       63
     * 1100 0010 0001 1101 1010 0110 0010 0110 0110 0011
     * 1D        A6        26       63 =》 497428067
     * @throws Exception
     */
    @Test
    public void ctimeTest() throws Exception {
        //set ngint '-40'
        //00 05 6e 67 69 6e 74 c0 d8
        byte dd = (byte)0xd8;

        System.out.println("result:"+ dd);
        byte[] xix = new byte[1];
        xix[0] = dd;
        byte[] bytes = String.valueOf(xix).getBytes(StandardCharsets.US_ASCII);
        System.out.println(new String(bytes, StandardCharsets.US_ASCII));
        //C2 1D A6 26 63
    }

    @Test
    public void rdbReadTest1() throws Exception {
        char xx = '\0';
        boolean result= RdbConstant.RDB_OPCODE_AUX == (11);
        //-5 & 0xff
        System.out.println(result);
    }
}
