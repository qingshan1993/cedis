package com.cedis.core.rdb.result;

import java.util.List;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/17
 * @desc rdb的结果
 */
public interface RdbResult {

    /**
     * 获取
     * @return
     */
    void setMagic(String magic);

    /**
     * 获取版本号
     * @return
     */
    String getMagic();

    /**
     * 获取rdb文件的版本号
     * @return
     */
    int getVersion();

    /**
     * 获取
     * @return
     */
    void setVersion(int rdbVer);

    List<Entry> getResult();

    List<Entry> getResult(EntryType entryType);

    Entry getResult(EntryType entryType, String key);


}
