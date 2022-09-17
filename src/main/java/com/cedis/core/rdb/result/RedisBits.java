package com.cedis.core.rdb.result;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/17
 * @desc
 */
public class RedisBits implements Entry {

    private String bits;

    public String get(){
        return this.bits;
    }

    @Override
    public EntryType getEntry() {
        return EntryType.REDIS_VER;
    }

    @Override
    public String toString() {
        return EntryType.REDIS_VER.getDefaultKey() +": " + this.bits;
    }
}
