package com.cedis.core.rdb.parser;

import com.cedis.core.rdb.parser.Parser;
import com.cedis.core.rdb.parser.Rdb8Parser;
import com.cedis.core.rdb.result.RdbResult;
import com.cedis.exception.ParseException;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/14
 * @desc according to redis version, choose appropriate rdb parser
 *       根据不同的版本，选择合适的rdb 解析器
 */
public class ParseDispatcher extends AbstractRdbParser {

    public ParseDispatcher() {
    }

    public ParseDispatcher(ReadableByteChannel ch, ByteBuffer buf, RdbResult rdbResult) {
        super(ch, buf, rdbResult);
    }

    /**
     * 读取rdb文件的版本号，分配给具体的解析器
     * @return
     */
    public Parser dispatch() {
        super.checkMagic();
        int fileRdbVer = super.readVersion();
        String parserName = this.getClass().getPackage().getName() + "Rdb" + fileRdbVer + "Parser";
        Class<?> parserClass = null;
        try {
            parserClass = Class.forName(parserName);
        } catch (ClassNotFoundException e) {
            throw new ParseException("there is no suitable parser, rdbVersion:" + fileRdbVer);
        }
        Parser parser = (Parser) BeanUtils.instantiateClass(parserClass);
        parser.setRdbResult(this.getRdbResult());
        parser.setFileSource(this.getCh(), this.getBuf());
        return parser;
    }

}
