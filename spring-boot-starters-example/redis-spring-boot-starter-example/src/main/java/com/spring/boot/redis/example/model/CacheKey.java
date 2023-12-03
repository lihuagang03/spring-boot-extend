package com.spring.boot.redis.example.model;

import lombok.Data;

/**
 * 缓存的键
 *
 * @author guang.yi
 * @since 2023/12/3
 */
@Data
public class CacheKey {

    private String cacheName;

    private String keyPattern;

}
