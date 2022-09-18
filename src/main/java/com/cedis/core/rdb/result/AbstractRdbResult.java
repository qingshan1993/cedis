package com.cedis.core.rdb.result;

import java.util.List;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/17
 * @desc
 */
public abstract class AbstractRdbResult implements RdbResult {

    @Override
    public int getVersion() {
        return 0;
    }


    public String setVersion() {
        return null;
    }

    @Override
    public List<Entry> getResult() {
        return null;
    }

    @Override
    public List<Entry> getResult(EntryType entryType) {
        return null;
    }

    @Override
    public Entry getResult(EntryType entryType, String key) {
        return null;
    }
}
