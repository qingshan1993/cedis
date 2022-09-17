package com.cedis.core.rdb;

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
 * @desc 文件rdb源
 */
public  class FileRdbResource {

    private final ReadableByteChannel ch;

    private ByteBuffer buf ;

    private final static int DEFAULT_BUFFER_SIZE = 64*1024 ;

    public FileRdbResource(ReadableByteChannel ch) {
        this.ch = ch;
        this.buf = ByteBuffer.allocateDirect(DEFAULT_BUFFER_SIZE);
    }

    public FileRdbResource(ReadableByteChannel ch, int buffSize) {
        this.ch = ch;
        this.buf = ByteBuffer.allocateDirect(buffSize);
    }

    public FileRdbResource(Path path) throws IOException {
        this.ch = FileChannel.open(path, StandardOpenOption.READ);
        this.buf = ByteBuffer.allocateDirect(DEFAULT_BUFFER_SIZE);
    }

    public FileRdbResource(Path path, int buffSize) throws IOException {
        this.ch = FileChannel.open(path, StandardOpenOption.READ);
        this.buf = ByteBuffer.allocateDirect(buffSize);
    }

    public FileRdbResource(File file) throws IOException {
        this(file.toPath(), DEFAULT_BUFFER_SIZE);
    }
    public FileRdbResource(File file, int buffSize) throws IOException {
        this(file.toPath(), buffSize);
    }

    public FileRdbResource(InputStream inputStream, int buffSize){
        this(Channels.newChannel(inputStream), buffSize);
    }

    public FileRdbResource(String filename) throws IOException {
        this(new File(filename), DEFAULT_BUFFER_SIZE);
    }

    public FileRdbResource(String filename, int buffSize) throws IOException {
        this(new File(filename), buffSize);
    }
}
