package com.spring.boot.reactive.web.flux.controller;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

import com.spring.boot.reactive.web.flux.entity.Coffee;
import lombok.extern.slf4j.Slf4j;

/**
 * Create a RestController
 *
 * @author guang.yi
 * @since 2023/8/14
 */
@Slf4j
@RestController("coffeeController")
public class CoffeeController {

    private final ReactiveRedisOperations<String, Coffee> reactiveRedisOperations;

    public CoffeeController(
            ReactiveRedisOperations<String, Coffee> reactiveRedisOperations
    ) {
        this.reactiveRedisOperations = reactiveRedisOperations;
        log.info("create CoffeeController");
    }

    /**
     * <pre>
     * <a href="http://localhost:8386/coffees">http://localhost:8386/coffees</a>
     *
     * [
     *     {
     *         "id":"0c110d6d-f3b9-4005-83b0-bf418faf91c8",
     *         "name":"Black Alert Redis"
     *     },
     *     {
     *         "id":"c1cd8c13-9873-4c62-8430-08c42437e4e5",
     *         "name":"Darth Redis"
     *     },
     *     {
     *         "id":"6b19fa71-7a39-4844-8492-f1a68d6f0005",
     *         "name":"Jet Black Redis"
     *     }
     * ]
     * </pre>
     */
    @GetMapping("/coffees")
    public Flux<Coffee> all() {
        return reactiveRedisOperations.keys("*")
                .flatMap(reactiveRedisOperations.opsForValue()::get)
                ;
    }

}
