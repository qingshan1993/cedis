package com.cedis.core.rdb;

import com.cedis.core.rdb.parser.Parser;
import com.cedis.core.rdb.result.RdbResult;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/17
 * @desc ParseDispatcher Builder
 */
public class ParserBuilder {

    private boolean checkMagic;

    private RdbResult rdbResult;

    private boolean autoChooseVersion;

    private int version;

    private RdbResource rdbResource;

    private ParseDispatcher dispatcher;

    private Parser build(){
        return new ParseDispatcher();
    }


}
