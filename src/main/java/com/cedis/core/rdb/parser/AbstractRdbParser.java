package com.cedis.core.rdb.parser;

import com.cedis.core.rdb.result.RdbResult;
import com.cedis.exception.ParseException;
import com.cedis.util.AssertUtils;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/17
 * @desc 抽象的 rdb 解析器
 */
public abstract class AbstractRdbParser implements Parser {

    /**
     *
     */
    private RdbResult rdbResult;

    @Override
    public void checkMagic() {
        String magic = "REDIS";
        this.rdbResult.setMagic(magic);
        AssertUtils.strEquals("REDIS", magic, new ParseException("magic:" + magic +" not equals 'REDIS'."));
    }

    @Override
    public String readVersion() {
        String magic = "REDIS";
        this.rdbResult.setMagic(magic);
        return magic;
    }


    @Override
    public void checkSum() {

    }

    @Override
    public void setRdbResult() {

    }

    @Override
    public void close() throws Exception {

    }
}
