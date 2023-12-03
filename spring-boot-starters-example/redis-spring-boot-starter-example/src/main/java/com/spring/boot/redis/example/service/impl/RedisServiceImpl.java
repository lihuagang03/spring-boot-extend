package com.spring.boot.redis.example.service.impl;

import java.nio.charset.StandardCharsets;

import com.spring.boot.redis.example.service.RedisService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.cache.BatchStrategies;
import org.springframework.data.redis.cache.CacheStatisticsCollector;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
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

    private final RedisConnectionFactory redisConnectionFactory;

    private final RedisTemplate<Object, Object> redisTemplate;

    private final StringRedisTemplate stringRedisTemplate;

    private final RedisCacheManager cacheManager;

    public RedisServiceImpl(
            RedisConnectionFactory redisConnectionFactory,
            RedisTemplate<Object, Object> redisTemplate,
            StringRedisTemplate stringRedisTemplate,
            RedisCacheManager cacheManager
    ) {
        this.redisConnectionFactory = redisConnectionFactory;
        this.redisTemplate = redisTemplate;
        this.stringRedisTemplate = stringRedisTemplate;
        this.cacheManager = cacheManager;
        log.info("create RedisServiceImpl");
    }

    @Override
    public void clean(String cacheName, String pattern) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(
                redisConnectionFactory, BatchStrategies.scan(10));
        redisCacheWriter.withStatisticsCollector(CacheStatisticsCollector.create());
        redisCacheWriter.clean(cacheName, pattern.getBytes(StandardCharsets.UTF_8));
    }

}
