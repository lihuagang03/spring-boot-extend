package com.spring.boot.redis.example.service;

import com.spring.boot.redis.example.model.CacheKey;

/**
 * 缓存服务
 *
 * @author guang.yi
 * @since 2023/7/30
 */
public interface CacheService {
    //

    /**
     * 清理Redis缓存的键模式
     * Remove all keys following the given pattern.
     *
     * @param cacheKey 缓存的键模式
     * @return 操作结果
     */
    boolean cleanCache(CacheKey cacheKey);

}
