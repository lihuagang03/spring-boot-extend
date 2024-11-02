package com.spring.boot.observability.example.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.spring.boot.observability.example.domain.entity.TradeEntity;
import com.spring.boot.observability.example.domain.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * Redis配置
 *
 * @since 2023/12/16
 * @see org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration
 * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
 */
@Slf4j
@EnableCaching
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore({
        RedisAutoConfiguration.class, RedisReactiveAutoConfiguration.class
})
public class RedisConfiguration {
    public RedisConfiguration() {
        log.info("create RedisConfiguration");
    }

    // RedisReactiveAutoConfiguration

//    /**
//     * {@code @ConditionalOnMissingBean(name = "reactiveRedisTemplate")}
//     * 表示组件可覆盖
//     *
//     * @see org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration#reactiveRedisTemplate(ReactiveRedisConnectionFactory, ResourceLoader)
//     */
//    @Bean
//    public ReactiveRedisTemplate<String, UserEntity> userReactiveRedisTemplate(
//            ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
//        log.info("call userReactiveRedisTemplate()");
//
//        // 对象序列化/反序列化
//        // 默认是 RedisSerializer.java()
//
//        // RedisSerializer.string()
//        // StringRedisSerializer.UTF_8
//        // Jackson2JsonRedisSerializer<>
//        RedisSerializer<UserEntity> redisSerializer = new Jackson2JsonRedisSerializer<>(UserEntity.class);
//
//        RedisSerializationContext<String, UserEntity> serializationContext = RedisSerializationContext
//                .<String, UserEntity>newSerializationContext(RedisSerializer.string())
//                .value(redisSerializer)
//                .build();
//        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, serializationContext);
//    }
//
//    @Bean
//    public ReactiveRedisTemplate<String, TradeEntity> tradeReactiveRedisTemplate(
//            ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
//        log.info("call tradeReactiveRedisTemplate()");
//
//        // Jackson2JsonRedisSerializer<>
//        RedisSerializer<TradeEntity> redisSerializer = new Jackson2JsonRedisSerializer<>(TradeEntity.class);
//
//        RedisSerializationContext<String, TradeEntity> serializationContext = RedisSerializationContext
//                .<String, TradeEntity>newSerializationContext(RedisSerializer.string())
//                .value(redisSerializer)
//                .build();
//        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, serializationContext);
//    }

    // RedisAutoConfiguration

    /**
     * {@code @ConditionalOnMissingBean(name = "redisTemplate")}
     * 表示组件可覆盖
     *
     * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration#redisTemplate(RedisConnectionFactory)
     * @see RedisTemplate#afterPropertiesSet()
     */
    @Bean
    public RedisTemplate<String, UserEntity> userRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        log.info("call userRedisTemplate()");

        RedisTemplate<String, UserEntity> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // Jackson2JsonRedisSerializer<>
        RedisSerializer<UserEntity> redisSerializer = new Jackson2JsonRedisSerializer<>(UserEntity.class);

        // 对象序列化/反序列化
        // 默认是 RedisSerializer.java()
        template.setDefaultSerializer(RedisSerializer.string());
//        template.setKeySerializer(RedisSerializer.string());
//        template.setValueSerializer(RedisSerializer.json());
        template.setValueSerializer(redisSerializer);
//        template.setHashKeySerializer(RedisSerializer.string());
        template.setHashValueSerializer(RedisSerializer.json());

        return template;
    }

    @Bean
    public RedisTemplate<String, TradeEntity> tradeRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        log.info("call tradeRedisTemplate()");

        RedisTemplate<String, TradeEntity> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        RedisSerializer<TradeEntity> redisSerializer = new Jackson2JsonRedisSerializer<>(TradeEntity.class);

        // 对象序列化/反序列化
        // 默认是 RedisSerializer.java()
        template.setDefaultSerializer(RedisSerializer.string());
//        template.setKeySerializer(RedisSerializer.string());
//        template.setValueSerializer(RedisSerializer.json());
        template.setValueSerializer(redisSerializer);
//        template.setHashKeySerializer(RedisSerializer.string());
        template.setHashValueSerializer(RedisSerializer.json());

        return template;
    }

    /**
     * {@code @ConditionalOnMissingBean(name = "redisTemplate")}
     * 表示组件可覆盖
     *
     * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration#redisTemplate(RedisConnectionFactory)
     * @see RedisTemplate#afterPropertiesSet()
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        log.info("call redisTemplate()");

        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        RedisSerializer<Object> redisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        // 对象序列化/反序列化
        // 默认是 RedisSerializer.java()
        template.setDefaultSerializer(RedisSerializer.string());
        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(RedisSerializer.json());
        template.setHashKeySerializer(RedisSerializer.string());
        template.setHashValueSerializer(redisSerializer);

        return template;
    }

    /**
     * {@code @ConditionalOnMissingBean}
     * 表示组件可覆盖
     * <pre>
     * 	public StringRedisTemplate() {
     * 		setKeySerializer(RedisSerializer.string());
     * 		setValueSerializer(RedisSerializer.string());
     * 		setHashKeySerializer(RedisSerializer.string());
     * 		setHashValueSerializer(RedisSerializer.string());
     * }
     * </pre>
     *
     * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration#stringRedisTemplate(RedisConnectionFactory)
     * @see StringRedisTemplate#StringRedisTemplate()
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("call stringRedisTemplate()");

        // 对象序列化/反序列化
        // 默认是 RedisSerializer.string()
//        return new StringRedisTemplate(redisConnectionFactory);

        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(RedisSerializer.string());
        return template;
    }
}
