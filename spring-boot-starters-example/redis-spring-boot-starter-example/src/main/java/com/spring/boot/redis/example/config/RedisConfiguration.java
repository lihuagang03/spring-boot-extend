package com.spring.boot.redis.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Redis配置
 *
 * @author guangyi
 * @date 2023/7/30
 * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class RedisConfiguration {

    public RedisConfiguration() {
        log.info("create RedisConfiguration");
    }

    /**
     * {@code @ConditionalOnMissingBean(name = "redisTemplate")}
     * 表示组件可覆盖
     *
     * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration#redisTemplate(RedisConnectionFactory)
     * @see RedisTemplate#afterPropertiesSet()
     */
    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // 序列化程序
        // 默认是 RedisSerializer.java()

//        template.setKeySerializer(RedisSerializer.string());
//        template.setValueSerializer(RedisSerializer.string());
//        template.setHashKeySerializer(RedisSerializer.string());
//        template.setHashValueSerializer(RedisSerializer.string());

        return template;
    }

    /**
     * {@code @ConditionalOnMissingBean}
     * 表示组件可覆盖
     *
     * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration#stringRedisTemplate(RedisConnectionFactory)
     * @see StringRedisTemplate#StringRedisTemplate()
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 序列化程序
        // 默认是 RedisSerializer.string()
        return new StringRedisTemplate(redisConnectionFactory);
    }
}
