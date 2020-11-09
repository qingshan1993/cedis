package com.cedis.core.rdb;

import org.junit.jupiter.api.Test;

import java.io.File;
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
}
