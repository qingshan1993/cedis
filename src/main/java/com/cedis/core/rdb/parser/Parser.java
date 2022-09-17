package com.cedis.core.rdb.parser;

import com.cedis.core.rdb.result.RdbResult;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/7
 * @desc rdb文件解析器,鉴于rdb文件大小不确定,并不会一次性将文件读入内存,而是采取分段读取的机制
 */
public interface Parser extends AutoCloseable {

    void checkMagic();

    String readVersion();

    RdbResult readComplete();

    void checkSum();

    void setRdbResource();

    void setRdbResult();

    void getRdbResult();









}
