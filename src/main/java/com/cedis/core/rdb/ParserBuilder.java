package com.cedis.core.rdb;

import com.cedis.core.rdb.parser.ParseDispatcher;
import com.cedis.core.rdb.parser.Parser;
import com.cedis.core.rdb.result.GenericRdbResult;
import com.cedis.core.rdb.result.RdbResult;
import com.cedis.exception.ParseException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * @author qingshan1993
 * @version 1.0
 * @date 2022/9/17
 * @desc ParseDispatcher Builder
 */
public class ParserBuilder {

    public Parser parser;

    private RdbResult rdbResult;

    private int version;

    private int bufferSize = 64*1024;

    private ReadableByteChannel ch;

    private ByteBuffer buf ;

    public Parser build() {

        // config Parser
        this.parser = new ParseDispatcher();

        // config RdbResult
        if (this.rdbResult == null) {
            this.rdbResult = new GenericRdbResult();
        }
        this.parser.setRdbResult(this.rdbResult);

        //config file
        if (this.ch == null) {
            throw new ParseException("sorry, there is no file source");
        } else {
            this.parser.setRdbResult(this.rdbResult);
        }
        this.buf = ByteBuffer.allocateDirect(this.bufferSize);

        Parser realParser = ((ParseDispatcher) this.parser).dispatch();

        return realParser;
    }

    public ParserBuilder version(int version) {
        this.version = version;
        return this;
    }


    public ParserBuilder RdbResource(RdbResult rdbResult) {
        this.rdbResult = rdbResult;
        return this;
    }

    public ParserBuilder bufferSize(int bufferSize) {
        if (bufferSize <= 0) {
            throw new ParseException("bufferSize must > 0");
        } else {
            this.bufferSize = bufferSize;
        }
        return this;
    }

    public ParserBuilder fileSource(ReadableByteChannel ch) {
        this.ch = ch;
        return this;
    }

    public ParserBuilder fileSource(Path path) {
        try {
            this.ch = FileChannel.open(path, StandardOpenOption.READ);
        } catch (IOException e) {
            throw new ParseException("IO Exception,path:"+ path);
        }
        return this;
    }

    public ParserBuilder fileSource(File file) {
        try {
            this.ch = FileChannel.open(file.toPath(), StandardOpenOption.READ);
        } catch (IOException e) {
            throw new ParseException("IO Exception,file:"+ file.getAbsolutePath());
        }
        return this;
    }

    public ParserBuilder fileSource(InputStream inputStream) {
        this.ch = Channels.newChannel(inputStream);
        return this;
    }

    public ParserBuilder fileSource(String filename) {
        try {
            this.ch = FileChannel.open(new File(filename).toPath(), StandardOpenOption.READ);
        } catch (IOException e) {
            throw new ParseException("IO Exception,filename:" + filename);
        }
        return this;
    }




}
