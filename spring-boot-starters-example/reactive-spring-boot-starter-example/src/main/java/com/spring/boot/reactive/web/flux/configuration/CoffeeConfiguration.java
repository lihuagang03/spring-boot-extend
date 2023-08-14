package com.spring.boot.reactive.web.flux.configuration;

import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.spring.boot.reactive.web.flux.entity.Coffee;
import lombok.extern.slf4j.Slf4j;

/**
 * Create a Configuration Class
 * 咖啡配置
 *
 * @author guang.yi
 * @since 2023/8/14
 * @see RedisReactiveAutoConfiguration
 * @see ReactiveRedisTemplate
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class CoffeeConfiguration {

    public CoffeeConfiguration() {
        log.info("create CoffeeConfiguration");
    }

    /**
     * 反应式的redis模板和操作
     *
     * @param reactiveRedisConnectionFactory 反应式的redis连接工厂
     * @return ReactiveRedisTemplate
     * @see RedisReactiveAutoConfiguration#reactiveRedisTemplate(ReactiveRedisConnectionFactory, ResourceLoader)
     */
    @Bean
    public ReactiveRedisOperations<String, Coffee> reactiveRedisOperations(
            ReactiveRedisConnectionFactory reactiveRedisConnectionFactory
    ) {
        Jackson2JsonRedisSerializer<Coffee> serializer = new Jackson2JsonRedisSerializer<>(Coffee.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, Coffee> builder =
                RedisSerializationContext.newSerializationContext(RedisSerializer.string());

        RedisSerializationContext<String, Coffee> serializationContext = builder.value(serializer)
                .build();

        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, serializationContext);
    }
}
