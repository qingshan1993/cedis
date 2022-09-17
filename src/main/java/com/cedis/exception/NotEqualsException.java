package com.cedis.exception;

/**
 * @author jjq
 * @version 1.0
 * @date 2022/3/4
 * @desc ParseException
 */
public class NotEqualsException extends RuntimeException {

    /**
     * Create a new ParseException with the specified message.
     * @param message the detail message
     */
    public NotEqualsException(String message) {
        super(message);
    }

    /**
     * Create a new ParseException with the specified message  and root cause.
     * @param message the detail message
     * @param cause the root cause
     */
    public NotEqualsException(String message, Throwable cause) {
        super(message, cause);
    }
}
