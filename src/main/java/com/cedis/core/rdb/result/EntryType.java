package com.cedis.core.rdb.result;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/17
 * @desc
 */
public enum EntryType {

    REDIS_VER("redis-ver"),

    REDIS_BIT("redis-bits"),

    CTIME("ctime"),

    AOP_PREAMBLE("redis-bits"),

    KV_PAIR("redis-bits"),

    SELECT_DB("redis-bits"),

    RESIZE_DB("redis-bits"),

    EOF("redis-bits"),

    ;


    private String defaultKey;


    EntryType(String defaultKey) {

        this.defaultKey = defaultKey;
    }

    public String getDefaultKey() {
        return defaultKey;
    }
}
