package com.cedis.core.rdb.result;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/17
 * @desc
 */
public enum EntryType {

    REDIS_VER("redis-ver", 0xfa),

    REDIS_BIT("redis-bits",0xfa),

    CTIME("ctime",0xfa),

    AOP_PREAMBLE("redis-bits",0xfa),

    KV_PAIR("redis-bits",0xfa),

    SELECT_DB("redis-bits",0xfa),

    RESIZE_DB("redis-bits",0xfa),

    EOF("redis-bits",0xfa),

    ;


    private String defaultKey;

    private int opcode;

    public static List<EntryType> getByOpcodeAndVer(int opcode, int rdbVer){
        List<EntryType> result = new ArrayList<>(8);
        for (EntryType et : EntryType.values()) {
            if (et.opcode == opcode) {
                result.add(et);
            }
        }
        return result;
    }

    EntryType(String defaultKey, int opcode) {
        this.defaultKey = defaultKey;
        this.opcode = opcode;
    }

    public String getDefaultKey() {
        return defaultKey;
    }

    public int getOpcode() {
        return opcode;
    }
}
