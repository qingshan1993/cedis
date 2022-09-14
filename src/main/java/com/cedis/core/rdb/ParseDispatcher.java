package com.cedis.core.rdb;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/14
 * @desc according to redis version, choose appropriate rdb parser
 * 根据不同的版本，选择合适的rdb 解析器
 */
public class ParseDispatcher implements Parser {

    @Override
    public String getMagic() {
        return null;
    }

    @Override
    public String getVision() {
        return null;
    }

    @Override
    public Long getCtime() {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
