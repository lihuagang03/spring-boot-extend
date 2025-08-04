package com.spring.boot.reactive.web.flux.cache;

import java.util.UUID;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

import com.spring.boot.reactive.web.flux.entity.Coffee;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

/**
 * Create a Spring Bean to Load Data
 * 数据缓存加载器
 *
 * @since 2023/8/14
 */
@Slf4j
@Component("coffeeLoader")
public class CoffeeLoader {
    private final ReactiveRedisConnectionFactory reactiveRedisConnectionFactory;
    private final ReactiveRedisOperations<String, Coffee> reactiveRedisOperations;

    public CoffeeLoader(
            ReactiveRedisConnectionFactory reactiveRedisConnectionFactory,
            ReactiveRedisOperations<String, Coffee> reactiveRedisOperations
    ) {
        this.reactiveRedisConnectionFactory = reactiveRedisConnectionFactory;
        this.reactiveRedisOperations = reactiveRedisOperations;
        log.info("create CoffeeLoader");
    }

    public static final String KEY_PREFIX = "coffee:";

    @PostConstruct
    public void loadData() {
        log.info("start loadData");
        reactiveRedisConnectionFactory.getReactiveConnection()
                .serverCommands()
                .flushAll()
                .thenMany(
                        Flux.just("Jet Black Redis", "Darth Redis", "Black Alert Redis")
                                .map(name -> new Coffee(KEY_PREFIX + UUID.randomUUID(), name))
                                .flatMap(coffee -> reactiveRedisOperations.opsForValue().set(coffee.id(), coffee)))
                .thenMany(reactiveRedisOperations.keys(KEY_PREFIX + "*")
                        .flatMap(reactiveRedisOperations.opsForValue()::get))
                .subscribe(System.out::println);
    }
}
