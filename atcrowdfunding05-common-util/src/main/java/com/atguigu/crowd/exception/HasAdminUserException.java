package com.atguigu.crowd.exception;

/**
 * @author 10185
 * @create 2021/2/4 14:30
 */
public class HasAdminUserException extends RuntimeException{
    public HasAdminUserException() {
        super();
    }

    public HasAdminUserException(String message) {
        super(message);
    }

    public HasAdminUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public HasAdminUserException(Throwable cause) {
        super(cause);
    }

    protected HasAdminUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
