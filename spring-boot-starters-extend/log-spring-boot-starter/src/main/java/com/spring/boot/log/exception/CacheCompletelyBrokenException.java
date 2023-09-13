package com.spring.boot.log.exception;

/**
 * 缓存未加载完成的损坏异常
 *
 * @author guang.yi
 * @since 2023/9/12
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
