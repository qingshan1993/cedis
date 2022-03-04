package com.cedis.core.rdb;

/**
 * @author jjq
 * @version 1.0
 * @date 2022/3/4
 * @desc ParseException
 */
public class ParseException extends RuntimeException {

    /**
     * Create a new ParseException with the specified message.
     * @param message the detail message
     */
    public ParseException(String message) {
        super(message);
    }

    /**
     * Create a new ParseException with the specified message  and root cause.
     * @param message the detail message
     * @param cause the root cause
     */
    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
