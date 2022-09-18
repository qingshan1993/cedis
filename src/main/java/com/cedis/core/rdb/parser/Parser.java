package com.cedis.core.rdb.parser;

import com.cedis.core.rdb.result.RdbResult;

import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/7
 * @desc rdb文件解析器,鉴于rdb文件大小不确定,并不会一次性将文件读入内存,而是采取分段读取的机制
 */
public interface Parser extends AutoCloseable {

    /**
     * 读取整个rdb文件，除了魔数和rdb的版本
     * read rdb file left except magic and rdb version
     * @return
     */
    RdbResult readComplete();

    void setFileSource(ReadableByteChannel ch, ByteBuffer buf);

    void setRdbResult(RdbResult rdbResult);

}
