package com.cedis.core.rdb;

import com.cedis.core.rdb.parser.Parser;
import com.cedis.core.rdb.parser.Rdb8Parser;
import com.cedis.core.rdb.result.RdbResult;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/14
 * @desc according to redis version, choose appropriate rdb parser
 *       根据不同的版本，选择合适的rdb 解析器
 */
public class ParseDispatcher implements Parser {

    public boolean checkMagic = false;

    @Override
    public RdbResult readComplete() {
        return null;
    }

    @Override
    public void checkMagic() {

    }

    @Override
    public String readVersion() {
        return null;
    }


    public Parser dispatch() {
        return null;
    }


    public Parser dispatch(RdbResult rdbResult) {
        if (checkMagic) {
            checkMagic();
        }
        String s = readVersion();
        instanceParser(s);
        return null;
    }

    @Override
    public void setRdbResource() {

    }

    /**
     *
     * @param s
     */
    private Parser instanceParser(String s) {
        //根据版本号初始化解析器
        return new Rdb8Parser();
    }

    public Parser dispatch(RdbResult rdbResult, BatchProperties.Job job) {
        return null;
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

    @Override
    public void getRdbResult() {

    }
}
