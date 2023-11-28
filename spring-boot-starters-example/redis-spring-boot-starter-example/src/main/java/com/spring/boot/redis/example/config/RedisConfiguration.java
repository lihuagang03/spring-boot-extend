package com.spring.boot.redis.example.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis配置
 *
 * @author guangyi
 * @since 2023/7/30
 * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
 * @see org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
 */
@Slf4j
@EnableCaching
@Configuration(proxyBeanMethods = false)
public class RedisConfiguration implements InitializingBean {

    private final RedisTemplate<Object, Object> redisTemplate;

    public RedisConfiguration(
            RedisTemplate<Object, Object> redisTemplate
    ) {
        this.redisTemplate = redisTemplate;
        log.info("create RedisConfiguration");
    }

    @Override
    public void afterPropertiesSet() {
        // 对象序列化/反序列化
        this.redisTemplate.setDefaultSerializer(RedisSerializer.string());
        this.redisTemplate.setKeySerializer(RedisSerializer.string());
        this.redisTemplate.setValueSerializer(RedisSerializer.json());
        this.redisTemplate.setHashKeySerializer(RedisSerializer.string());
        this.redisTemplate.setHashValueSerializer(RedisSerializer.string());
    }

    /**
     * {@link org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration#cacheManager(CacheProperties, CacheManagerCustomizers, ObjectProvider, ObjectProvider, RedisConnectionFactory, ResourceLoader)}
     * <pre>
     * ObjectProvider<org.springframework.data.redis.cache.RedisCacheConfiguration> redisCacheConfiguration
     * ObjectProvider<RedisCacheManagerBuilderCustomizer> redisCacheManagerBuilderCustomizers
     * 对象提供者，可自定义创建组件
     * </pre>
     *
     * @see org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration#createConfiguration
     */
    @Bean
    @ConditionalOnMissingBean
    public RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {
        // 对象序列化/反序列化
        // valueSerializationPair
        // 默认是 RedisSerializer.java()
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        // GenericJackson2JsonRedisSerializer
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));

        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }
        return config;
    }

//    /**
//     * {@code @ConditionalOnMissingBean(name = "redisTemplate")}
//     * 表示组件可覆盖
//     *
//     * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration#redisTemplate(RedisConnectionFactory)
//     * @see RedisTemplate#afterPropertiesSet()
//     */
//    @Bean
//    @ConditionalOnMissingBean(name = "redisTemplate")
//    @ConditionalOnSingleCandidate(RedisConnectionFactory.class)
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<Object, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//
//        // 对象序列化/反序列化
//        // 默认是 RedisSerializer.java()
//        template.setDefaultSerializer(RedisSerializer.string());
////        template.setKeySerializer(RedisSerializer.string());
//        template.setValueSerializer(RedisSerializer.json());
////        template.setHashKeySerializer(RedisSerializer.string());
////        template.setHashValueSerializer(RedisSerializer.string());
//
//        return template;
//    }

//    /**
//     * {@code @ConditionalOnMissingBean}
//     * 表示组件可覆盖
//     * <pre>
//     * 	public StringRedisTemplate() {
//     * 		setKeySerializer(RedisSerializer.string());
//     * 		setValueSerializer(RedisSerializer.string());
//     * 		setHashKeySerializer(RedisSerializer.string());
//     * 		setHashValueSerializer(RedisSerializer.string());
//     * }
//     * </pre>
//     *
//     * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration#stringRedisTemplate(RedisConnectionFactory)
//     * @see StringRedisTemplate#StringRedisTemplate()
//     */
//    @Bean
//    @ConditionalOnMissingBean
//    @ConditionalOnSingleCandidate(RedisConnectionFactory.class)
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        // 对象序列化/反序列化
//        // 默认是 RedisSerializer.string()
//        return new StringRedisTemplate(redisConnectionFactory);
//    }

}
