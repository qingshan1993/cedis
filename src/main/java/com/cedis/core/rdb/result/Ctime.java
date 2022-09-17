package com.cedis.core.rdb.result;

import java.time.format.DateTimeFormatter;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/17
 * @desc
 */
public class Ctime implements Entry {

    private long timestamp;

    public long get(){
        return this.timestamp;
    }


    public long format(){
        return this.timestamp;
    }

    public long format(DateTimeFormatter formatter){
        return this.timestamp;
    }

    @Override
    public EntryType getEntry() {
        return EntryType.CTIME;
    }

    @Override
    public String toString() {
        return EntryType.REDIS_VER.getDefaultKey() +": " + this.timestamp;
    }
}
