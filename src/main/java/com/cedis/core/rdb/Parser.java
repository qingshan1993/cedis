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
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/7
 * @desc rdb文件解析器,鉴于rdb文件大小不确定,并不会一次性将文件读入内存,而是采取分段读取的机制
 */
public interface Parser extends AutoCloseable {

    /**
     * 获取魔数：“REDIS”
     * @return
     */
    String getMagic();

    /**
     * 获取版本号
     * @return
     */
    String getVision();

    /**
     * 获取创建时间戳
     * get create timestamp （）
     * @return
     */
    Long getCtime();






}
