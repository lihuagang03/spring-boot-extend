package com.spring.boot.redis.example.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis配置
 *
 * @author guangyi
 * @since 2023/7/30
 * @see org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration
 * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
 * @see org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
 */
@Slf4j
@EnableCaching
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore({RedisAutoConfiguration.class, RedisReactiveAutoConfiguration.class})
public class RedisConfiguration {

    public RedisConfiguration() {
        log.info("create RedisConfiguration");
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
     * @see org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
     * @see com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer
     * @see org.springframework.data.redis.cache.CacheKeyPrefix#compute
     */
    @Bean
    @ConditionalOnMissingBean
    public RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {
        log.info("call redisCacheConfiguration()");

        // 对象序列化/反序列化
        // valueSerializationPair
        // 默认是 RedisSerializer.java()
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        // RedisSerializer.json()
        // GenericJackson2JsonRedisSerializer
        config = config.serializeValuesWith(SerializationPair.fromSerializer(RedisSerializer.json()));
//        // Jackson2JsonRedisSerializer - 不推荐，有坑！
//        config = config.serializeValuesWith(SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer<>(Object.class)));
        // GenericFastJsonRedisSerializer
//        config = config.serializeValuesWith(SerializationPair.fromSerializer(new GenericFastJsonRedisSerializer()));
//        // FastJsonRedisSerializer - 不推荐，有坑！
//        config = config.serializeValuesWith(SerializationPair.fromSerializer(new FastJsonRedisSerializer<>(Object.class)));

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
//        // CacheKeyPrefix#compute
//        config = config.computePrefixWith(cacheName -> cacheName + ':');
        return config;
    }

    // RedisReactiveAutoConfiguration

    /**
     * {@code @ConditionalOnMissingBean(name = "reactiveRedisTemplate")}
     * 表示组件可覆盖
     *
     * @see org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration#reactiveRedisTemplate(ReactiveRedisConnectionFactory, ResourceLoader)
     */
    @Bean
    @ConditionalOnMissingBean(name = "reactiveRedisTemplate")
//    @ConditionalOnBean(ReactiveRedisConnectionFactory.class)
    public ReactiveRedisTemplate<Object, Object> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        log.info("call reactiveRedisTemplate()");

        // 对象序列化/反序列化
        // 默认是 RedisSerializer.java()

        // RedisSerializer.string()
        // StringRedisSerializer.UTF_8
        // RedisSerializer.json()
        // GenericJackson2JsonRedisSerializer
        RedisSerializationContext<Object, Object> serializationContext = RedisSerializationContext
                .newSerializationContext(RedisSerializer.string())
                .value(RedisSerializer.json())
                .build();
        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory, serializationContext);
    }

    /**
     * {@code @ConditionalOnMissingBean(name = "reactiveStringRedisTemplate")}
     * 表示组件可覆盖
     *
     * @see org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration#reactiveStringRedisTemplate(ReactiveRedisConnectionFactory)
     */
    @Bean
    @ConditionalOnMissingBean(name = "reactiveStringRedisTemplate")
//    @ConditionalOnBean(ReactiveRedisConnectionFactory.class)
    public ReactiveStringRedisTemplate reactiveStringRedisTemplate(
            ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        log.info("call reactiveStringRedisTemplate()");

        // 对象序列化/反序列化
        // 默认是 RedisSerializer.string()
        // RedisSerializationContext.string()
        return new ReactiveStringRedisTemplate(reactiveRedisConnectionFactory);
    }

    // RedisAutoConfiguration

    /**
     * {@code @ConditionalOnMissingBean(name = "redisTemplate")}
     * 表示组件可覆盖
     *
     * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration#redisTemplate(RedisConnectionFactory)
     * @see RedisTemplate#afterPropertiesSet()
     */
    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
//    @ConditionalOnSingleCandidate(RedisConnectionFactory.class)
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("call redisTemplate()");

        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // 对象序列化/反序列化
        // 默认是 RedisSerializer.java()
        template.setDefaultSerializer(RedisSerializer.string());
//        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(RedisSerializer.json());
//        template.setHashKeySerializer(RedisSerializer.string());
//        template.setHashValueSerializer(RedisSerializer.string());

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
    @ConditionalOnMissingBean(name = "stringRedisTemplate")
//    @ConditionalOnSingleCandidate(RedisConnectionFactory.class)
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
