package com.cedis;

import com.cedis.core.lzf.LZFTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CedisApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(CedisApplication.class, args);
        if (args[0].equals("lzf-test")) {
            LZFTest lzfTest = new LZFTest();
            lzfTest.compress(args[1]);
        }
    }




}
