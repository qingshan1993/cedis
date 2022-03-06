package com.cedis.core.rdb;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jjq
 * @version 1.0
 * @date 2022/3/4
 * @desc rdb文件解析器,鉴于rdb文件大小不确定,并不会一次性将文件读入内存,而是采取分段读取的机制
 */
public class Parser implements Closeable{

    private String filePath;

    private FileChannel fileChannel;

    private ByteBuffer byteBuffer;

    private int bufferCapacity;

    private RDBInfo rdbInfo;

    /**
     * 当前处理的ByteBuffer.hp数组的index
     */
    private int curIndex = 0;

    /**
     * rdb文件的大小,一直累加
     */
    private long rdbSize = 0L;

    public Parser(String filePath, int bufferCapacity) throws FileNotFoundException {
        this.filePath = filePath;
        this.byteBuffer = ByteBuffer.allocate(bufferCapacity);
        this.fileChannel = new RandomAccessFile(filePath,"r").getChannel();
    }

    /**
     * verifyStringEqual 的重载方法
     * @param length
     * @param expect
     * @throws UnsupportedEncodingException
     */
    public void verifyStringEqual(int length, String expect) throws UnsupportedEncodingException {
        verifyStringEqual(this.curIndex, length, expect);
    }

    /**
     * 验证rdb文件某一段byte数组是否和expect字符串相等
     * @param offset byte数组的开始位置(偏移位置)
     * @param length byte数组的长度
     * @param expect 期望相等的字符春
     * 如果不相等将抛出异常信息
     */
    public void verifyStringEqual(int offset, int length, String expect) throws UnsupportedEncodingException {
        checkByteBufferBounds(length);
        String str = new String(this.byteBuffer.array(), this.curIndex, length, StandardCharsets.UTF_8.name());
        if (expect == null || !expect.equals(str)) {
            throw new ParseException("offset:"+ offset + "length:" + length +
                    ", str:"+ str +"not equal to expect:" + expect);
        }
        this.curIndex += length;
        this.rdbSize += length;
    }

    /**
     * 1.length长度的byte未超出position
     * 2. curIndex=12, position/capacity=16, length=6
     * 3.
     *
     * @param length
     */
    private void checkByteBufferBounds(int length) {
        if (this.curIndex + length > this.byteBuffer.position()) {
            //curIndex=12, position/capacity=16, length=6
            final int marginValue = (this.byteBuffer.position() - this.curIndex);
            if (marginValue + length > this.byteBuffer.capacity()) {
                throw new ParseException("length:"+ length + ", marginValue:" + marginValue
                        + ", capacity:"+ this.byteBuffer.capacity()
                        +";recursion read not permitted, advise to set bigger ByteBuffer.capacity!");
            }
            for (int i= 0;i < marginValue; i++) {
                this.byteBuffer.put(i, this.byteBuffer.get(this.byteBuffer.position()-1-i));
            }
            this.byteBuffer.position(marginValue);
            this.curIndex = 0;

            read();
        }
    }

    /**
     * read rdb file
     */
    public void read(){
        try {
            fileChannel.read(this.byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * close fileChannel
     */
    @Override
    public void  close() {
        try {
            this.fileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
