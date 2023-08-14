package com.spring.boot.reactive.web.flux.cache;

import javax.annotation.PostConstruct;

import java.util.UUID;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

import com.spring.boot.reactive.web.flux.entity.Coffee;
import lombok.extern.slf4j.Slf4j;

/**
 * Create a Spring Bean to Load Data
 * 数据缓存加载器
 *
 * @author guang.yi
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

    @PostConstruct
    public void loadData() {
        reactiveRedisConnectionFactory.getReactiveConnection()
                .serverCommands()
                .flushAll()
                .thenMany(
                        Flux.just("Jet Black Redis", "Darth Redis", "Black Alert Redis")
                                .map(name -> new Coffee(UUID.randomUUID().toString(), name))
                                .flatMap(
                                        coffee -> reactiveRedisOperations.opsForValue()
                                                .set(coffee.getId(), coffee)
                                )
                )
                .thenMany(
                        reactiveRedisOperations.keys("*")
                                .flatMap(reactiveRedisOperations.opsForValue()::get)
                )
                .subscribe(System.out::println)
        ;
    }
}
