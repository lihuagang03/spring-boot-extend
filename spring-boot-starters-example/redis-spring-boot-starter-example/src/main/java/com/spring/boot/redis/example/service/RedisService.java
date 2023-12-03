package com.spring.boot.redis.example.service;

/**
 * Redis服务
 *
 * @author guangyi
 * @since 2023/7/30
 */
public interface RedisService {
    //

    /**
     * Remove all keys following the given pattern.
     *
     * @param cacheName The cache name must not be {@literal null}.
     * @param pattern The pattern for the keys to remove. Must not be {@literal null}.
     */
    void clean(String cacheName, String pattern);

}
