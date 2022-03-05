package com.cedis.core.rdb;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jjq
 * @version 1.0
 * @date 2022/3/4
 * @desc rdb文件解析器
 */
public class Parser {

    private String filePath;

    private FileChannel fileChannel;

    private ByteBuffer byteBuffer;

    public Parser(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        this.byteBuffer = ByteBuffer.allocate(1024);
        this.fileChannel = new RandomAccessFile(filePath,"r").getChannel();
    }

    /**
     * 验证rdb文件某一段byte数组是否和expect字符串相等
     * @param start byte数组的开始位置
     * @param length byte数组的长度
     * @param expect 期望相等的字符春
     * 如果不相等将抛出异常信息
     */
    private void verifyStringEqual(int start, int length, String expect) {


    }

    /**
     * 读取文件流,返回指定区间的byte数组
     * @param start
     * @param length -1将不限制长度,0将抛出异常
     * @return
     */
    public byte[] readBytes(int start, int length) {
        if (start<0 || length == 0 || length < -1) {
            throw new IllegalArgumentException("variable 'start' value must >= 0;" +
                    "variable 'length' value must >0 or ==-1.");
        }
        byte[] result = null;
        if (length>0) {
            result = new byte[length];
        }else {
            result = new byte[1024];
        }
        try{
            ByteArrayInputStream rdb = null;
            int read = rdb.read(result);

        }catch (Exception e) {

        }finally {

        }
        List<Byte> ll = new ArrayList<>();
        List<byte[]> bytes = Arrays.asList(result);


        return result;
    }
}
