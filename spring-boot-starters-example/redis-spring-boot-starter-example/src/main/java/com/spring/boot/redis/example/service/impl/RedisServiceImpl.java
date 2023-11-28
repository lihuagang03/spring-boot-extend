package com.spring.boot.redis.example.service.impl;

import com.spring.boot.redis.example.service.RedisService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Redis服务实现
 *
 * @author guangyi
 * @since 2023/7/30
 */
@Slf4j
@Service("redisService")
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<Object, Object> redisTemplate;

    private final StringRedisTemplate stringRedisTemplate;

    private final RedisCacheManager cacheManager;

    public RedisServiceImpl(
            RedisTemplate<Object, Object> redisTemplate,
            StringRedisTemplate stringRedisTemplate,
            RedisCacheManager cacheManager
    ) {
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
        this.cacheManager = cacheManager;
        log.info("create RedisServiceImpl");
    }
}
