package com.spring.boot.redis.example.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.spring.boot.redis.example.model.CacheKey;
import com.spring.boot.redis.example.service.CacheService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.cache.BatchStrategies;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

/**
 * 缓存服务实现
 *
 * @author guang.yi
 * @since 2023/7/30
 */
@Slf4j
@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    /**
     * 并发开关
     */
    private final ConcurrentMap<String, Boolean> concurrentSwitch = new ConcurrentHashMap<>(16);

    private final ExecutorService executorService = new ThreadPoolExecutor(
            1, 1, 5L, TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(1),
            new ThreadFactoryBuilder().setNamePrefix("cache-clean-")
                    .setDaemon(true).build()
    );

    private final RedisConnectionFactory redisConnectionFactory;

    public CacheServiceImpl(
            RedisConnectionFactory redisConnectionFactory
    ) {
        this.redisConnectionFactory = redisConnectionFactory;
        log.info("create CacheServiceImpl");
    }

    @Override
    public boolean cleanCache(CacheKey cacheKey) {
        String keyPattern = cacheKey.getKeyPattern();
        // 避免多次重复地操作
        if (concurrentSwitch.putIfAbsent(keyPattern, Boolean.TRUE) == null) {
            // 异步地执行
            executorService.execute(() -> this.clean(cacheKey));

            return true;
        }
        return false;
    }

    private void clean(CacheKey cacheKey) {
        log.info("cleanCache start, cacheKey={}", cacheKey);
        StopWatch stopWatch = new StopWatch("cleanCache");
        stopWatch.start();

        this.clean(cacheKey.getCacheName(), cacheKey.getKeyPattern());

        stopWatch.stop();
        log.info("cleanCache end, cacheKey={}, stopWatch={}", cacheKey, stopWatch);
    }

    /**
     * 缓存Redis的历史数据清理
     * <pre>
     * 【批量策略】在线异步地扫描批量删除，每批10个key
     * 先SCAN，再批量DEL
     * 【执行策略】预发环境，业务低峰时期
     * </pre>
     *
     * @see org.springframework.data.redis.cache.RedisCacheWriter#clean
     * @see org.springframework.data.redis.cache.DefaultRedisCacheWriter#clean
     */
    private void clean(String cacheName, String keyPattern) {
        // 【批量策略】SCAN，每批10个key
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(
                redisConnectionFactory, BatchStrategies.scan(10));
        // 先SCAN，再批量DEL
        redisCacheWriter.clean(cacheName, keyPattern.getBytes(StandardCharsets.UTF_8));
    }

}
