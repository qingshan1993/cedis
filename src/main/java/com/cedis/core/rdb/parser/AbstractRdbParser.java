package com.cedis.core.rdb.parser;

import com.cedis.core.rdb.result.EntryType;
import com.cedis.core.rdb.result.RdbResult;
import com.cedis.exception.ParseException;
import com.cedis.util.AssertUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/17
 * @desc 抽象的 rdb 解析器
 */
public abstract class AbstractRdbParser implements Parser {

    private ReadableByteChannel ch;

    private ByteBuffer buf;

    private RdbResult rdbResult;

    private long bytesBuffered = 0;

    public AbstractRdbParser() {
    }

    public AbstractRdbParser(ReadableByteChannel ch, ByteBuffer buf, RdbResult rdbResult) {
        this.ch = ch;
        this.buf = buf;
        this.rdbResult = rdbResult;
    }

    @Override
    public void setFileSource(ReadableByteChannel ch, ByteBuffer buf) {
        this.ch = ch;
        this.buf = buf;
    }

    @Override
    public RdbResult readComplete() {
        //read head info, eg: redis-ver, ctime, redis-bites
        readByOpCode(0xfa);

        return null;
    }

    private void readByOpCode(int i) {
        int opcode = i;
        switch (opcode) {
            case 0xfa :
                readHeadInfo();
            case 0xfe:
                readHeadInfo();
            default:
                System.out.println("default");

        }
    }

    private void readHeadInfo() {
        List<EntryType> auxFieldList = EntryType.getByOpcodeAndVer(0xfa, this.getRdbResult().getVersion());
        while (readOneBytes(0xfa)) {
            readStringEncoded();
            //auxFieldList.stream().filter(x->)
        }

    }


    private byte[] readStringEncoded() {
        //获取第一个字节
        byte firstByte = readOneBytes();
        //读取第一个字节的前两个bit位
        int flag = (firstByte & 0xc0) >> 6;
        int len = 0;
        switch (flag){
            case 0:// 00|xxxxxx 长度是剩下的6个bit
                len = firstByte & 0x3f;
                return readBytes(len);
            case 1:// 01|xxxxxx 长度是剩下的6个bit再加上下一个字节的8个bit，一共14个bit
                len = ((firstByte & 0x3f) << 8) | (readOneBytes() & 0xff);
                return readBytes(len);
            case 2:// 10|xxxxxx 舍弃剩余的6bit，长度是接下来的4个字节
                // 10|000000: len is a 32-bit integer encoded on the next 4 bytes.
                byte[] bs = readBytes(4);
                len = ((int)bs[0] & 0xff) << 24
                        | ((int)bs[1] & 0xff) << 16
                        | ((int)bs[2] & 0xff) <<  8
                        | ((int)bs[3] & 0xff) <<  0;
                if (len < 0) {
                    throw new ParseException("Strings longer than " + Integer.MAX_VALUE + "bytes are not supported.");
                }
                return readBytes(len);
            case 3:// 11|xxxxxx 具体代表什么取决剩下的6bit的值，有0,1,2,3四种可能
                int leftSixBit = firstByte & 0x3f;
                switch (leftSixBit){
                    case 0: //11|000000 代表了8bit位的数字型字符串
                    case 1: //11|000001 代表了16bit位的数字型字符串
                    case 2: //11|000010 代表了32bit位的数字型字符串
                    case 3: //11|000011 代表接下来是一个字符串经过lzf算法压缩后的byte数组
            }

        }



        return new byte[1];

    }

    @Override
    public void setRdbResult(RdbResult rdbResult) {
        this.rdbResult = rdbResult;
    }


    public RdbResult getRdbResult() {
        return this.rdbResult;
    }


    /**
     * read rdb file magic
     * eg: 52 45 44 49 53 => 'REDIS'
     */
    public void checkMagic() {
        String magic = new String(readBytes(5), StandardCharsets.US_ASCII);
        AssertUtils.strEquals("REDIS", magic, new ParseException("magic:" + magic +" not equals 'REDIS'."));
        this.rdbResult.setMagic(magic);
    }


    /**
     * read rdb version
     * eg: 30 30 30 38 => '0008'
     * @return
     */
    public int readVersion() {
        String rdbVerStr = new String(readBytes(4), StandardCharsets.US_ASCII);
        int rdbVer = Integer.parseInt(rdbVerStr);
        this.rdbResult.setVersion(rdbVer);
        return rdbVer;
    }



    public void checkSum() {

    }


    @Override
    public void close() throws Exception {

    }

    public ReadableByteChannel getCh() {
        return ch;
    }

    public ByteBuffer getBuf() {
        return buf;
    }

    /**
     * 读取指定长度的二进制数据，如果buf中的剩余可读长度太小，将从文件中再次读取长度为buf.capacity()的内容，直到满足要求为止
     * @param numBytes
     * @return
     * @throws IOException
     */
    private byte[] readBytes(int numBytes){
        int needRead = numBytes;
        byte[] bs = new byte[numBytes];
        int bsPos = 0; //bs数组已读取到数据的位置
        while (needRead > 0) {
            int bufRemain = buf.remaining();
            if (bufRemain >= needRead) { //检查buf中剩余长度是否满足要求
                buf.get(bs, bsPos, needRead); //从buff的position+pos为止开始，读取rem长度个字节
                needRead = 0;//将needRead置为0，将推出循环
            } else {
                buf.get(bs, bsPos, bufRemain); //读取剩余的全部字节
                bsPos += bufRemain; //更新结果数组bs已读取的位置
                needRead -= bufRemain;//更新剩余需要读取的长度
                fillBuffer();
            }
        }
        return bs;
    }

    /**
     * 读取一个字节
     * read one bit
     * @return
     * @throws IOException
     */
    private byte readOneBytes(){
        return readBytes(1)[0];
    }

    /**
     * 读取一个字节,将结果和入参expect比较，如果不相等，将buf的position回退1
     * if result equals expect ,return true
     * otherwise return false, and reset buf.position
     * @return
     * @throws IOException
     */
    private boolean readOneBytes(int expect){
        byte res =  readBytes(1)[0];
        if (res == expect) {
            return true;
        }else {
            this.buf.position(this.buf.position()-1);
            return false;
        }
    }



    /**
     * 重新将buf读满
     */
    private void fillBuffer(){
        buf.clear();
        long n;
        try {
            n = ch.read(buf);
        } catch (IOException e) {
            throw new ParseException("IO Exception，buf read fail");
        }
        if (n == -1) {
            throw new ParseException("buf read result is -1");
        }
        bytesBuffered += n;
        buf.flip();
    }


}
