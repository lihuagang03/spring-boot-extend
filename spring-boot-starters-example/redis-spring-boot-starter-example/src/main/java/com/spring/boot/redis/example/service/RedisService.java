package com.spring.boot.redis.example.service;

import com.spring.boot.redis.example.model.CacheKey;

/**
 * Redis服务
 *
 * @author guang.yi
 * @since 2023/7/30
 */
public interface RedisService {
    //

    /**
     * Remove all keys following the given pattern.
     *
     * @param cacheKey 缓存的键模式
     */
    void cleanCache(CacheKey cacheKey);

}
