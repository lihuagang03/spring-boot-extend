package com.spring.boot.log.exception;

/**
 * 缓存未加载完成的损坏异常
 *
 * @see java.lang.RuntimeException
 */
public class CacheCompletelyBrokenException extends RuntimeException {
    public CacheCompletelyBrokenException(String message) {
        super(message);
    }

    public CacheCompletelyBrokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
