package com.spring.boot.redis.example.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.spring.boot.redis.example.model.CacheKey;
import com.spring.boot.redis.example.service.RedisService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.cache.BatchStrategies;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

/**
 * Redis服务实现
 *
 * @author guang.yi
 * @since 2023/7/30
 */
@Slf4j
@Service("redisService")
public class RedisServiceImpl implements RedisService {

    private final ExecutorService executorService = new ThreadPoolExecutor(
            1, 1, 5L, TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(1),
            new ThreadFactoryBuilder().setNamePrefix("redis-cache-clean-")
                    .setDaemon(true).build()
    );

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
    public void cleanCache(CacheKey cacheKey) {
        // 异步地执行
        executorService.execute(
                () -> this.cleanCache(cacheKey.getCacheName(), cacheKey.getKeyPattern())
        );
    }

    /**
     * 异步地扫描批量删除，每批10个key
     * 先scan，再批量del
     * <p>
     * Remove all keys following the given pattern.
     *
     * @param cacheName The cache name must not be {@literal null}.
     * @param pattern   The pattern for the keys to remove. Must not be {@literal null}.
     * @see org.springframework.data.redis.cache.RedisCacheWriter#clean
     * @see org.springframework.data.redis.cache.DefaultRedisCacheWriter#clean
     */
    private void cleanCache(String cacheName, String pattern) {
        // 每批10个key
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(
                redisConnectionFactory, BatchStrategies.scan(10));

        log.info("cleanCache start");
        StopWatch stopWatch = new StopWatch("cleanCache");
        stopWatch.start();

        redisCacheWriter.clean(cacheName, pattern.getBytes(StandardCharsets.UTF_8));

        stopWatch.stop();
        log.info("cleanCache end, stopWatch={}", stopWatch);
    }

}
