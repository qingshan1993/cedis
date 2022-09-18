package com.cedis.core.rdb.parser;

import com.cedis.core.rdb.result.RdbResult;

import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/17
 * @desc rdb version 8 parser
 */
public class Rdb8Parser extends AbstractRdbParser implements Parser {


    public Rdb8Parser() {
    }

    public Rdb8Parser(ReadableByteChannel ch, ByteBuffer buf, RdbResult rdbResult) {
        super(ch, buf, rdbResult);
    }

    @Override
    public RdbResult readComplete() {
        //read head info, eg: redis-ver, ctime, redis-bites
        readByAuxField(0xfa);

        return null;
    }

    private void readByAuxField(int i) {

    }

    @Override
    public RdbResult getRdbResult() {
        return super.getRdbResult();

    }

    @Override
    public void setFileSource(ReadableByteChannel ch, ByteBuffer buf) {

    }

    @Override
    public void setRdbResult(RdbResult rdbResult) {

    }
}
