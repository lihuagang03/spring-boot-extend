package com.spring.boot.redis.example.model;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 缓存的键
 *
 * @author guang.yi
 * @since 2023/12/3
 */
@Data
public class CacheKey {

    @NotEmpty(message = "cacheName must be not empty")
    private String cacheName;

    @NotEmpty(message = "keyPattern must be not empty")
    private String keyPattern;

}
