package com.cedis.constant;

/**
 * @author jjq
 * @version 1.0
 * @date 2022/3/6
 * @desc RDB 文件常量 参考redis/src/rdb.h
 */
public interface RdbConstant {


    /** RDB文件的特殊操作符(opcode)*/
    byte RDB_OPCODE_MODULE_AUX    = -9;   /* (byte)247 Module auxiliary data. */
    byte RDB_OPCODE_IDLE          = -8;   /* (byte)248 LRU idle time. */
    byte RDB_OPCODE_FREQ          = -7;   /* (byte)249 LFU frequency. */
    byte RDB_OPCODE_AUX           = -6;   /* (byte)250 RDB aux field. */
    byte RDB_OPCODE_RESIZEDB      = -5;   /* (byte)251 Hash table resize hbyte. */
    byte RDB_OPCODE_EXPIRETIME_MS = -4;   /* (byte)252 Expire time in milliseconds. */
    byte RDB_OPCODE_EXPIRETIME    = -3;   /* (byte)253 Old expire time in seconds. */
    byte RDB_OPCODE_SELECTDB      = -2;   /* (byte)254 DB number of the following keys. */
    byte RDB_OPCODE_EOF           = -1;   /* (byte)255 End of the RDB file. */

    /** RDB文件数据类型*/
    byte RDB_TYPE_STRING   = 0;
    byte RDB_TYPE_LIST     = 1;
    byte RDB_TYPE_SET      = 2;
    byte RDB_TYPE_ZSET     = 3;
    byte RDB_TYPE_HASH     = 4;
    byte RDB_TYPE_ZSET_2   = 5; /* ZSET version 2 with doubles stored in binary. */
    byte RDB_TYPE_MODULE   = 6;
    /* Module value with annotations for parsing without the generating module being loaded. */
    byte RDB_TYPE_MODULE_2 = 7;

    /* Object types for encoded objects. */
    byte RDB_TYPE_HASH_ZIPMAP      = 9;
    byte RDB_TYPE_LIST_ZIPLIST     = 10;
    byte RDB_TYPE_SET_INTSET       = 11;
    byte RDB_TYPE_ZSET_ZIPLIST     = 12;
    byte RDB_TYPE_HASH_ZIPLIST     = 13;
    byte RDB_TYPE_LIST_QUICKLIST   = 14;
    byte RDB_TYPE_STREAM_LISTPACKS = 15;

}
