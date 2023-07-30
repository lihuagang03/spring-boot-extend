package com.spring.boot.redis.example;

import javax.annotation.Resource;
import java.time.Duration;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link RedisTemplate}.
 *
 * @author guangyi
 * @date 2023/7/30
 * @see RedisTemplate
 * @see StringRedisTemplate
 */
@SpringBootTest(classes = RedisApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RedisTemplateTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final Duration CACHE_TIMEOUT = Duration.ofDays(1L);

    // Strings

    /**
     * <pre>
     * 序列化程序
     * RedisSerializer.java()
     * key = "\xac\xed\x00\x05t\x00\x13java:counter:user:1"
     * value = "\xac\xed\x00\x05sr\x00\x0ejava.lang.Long;\x8b\xe4\x90\xcc\x8f#\xdf\x02\x00\x01J\x00\x05valuexr\x00\x10java.lang.Number\x86\xac\x95\x1d\x0b\x94\xe0\x8b\x02\x00\x00xp\x00\x00\x00\x00\x00\x00\x00\x02"
     * </pre>
     */
    @Test
    @Order(0)
    void set() {
        String key = "java:counter:user:1";
        Long value = 2L;
        redisTemplate.opsForValue()
                .set(key, value, CACHE_TIMEOUT);
    }

    @Test
    @Order(100)
    void get() {
        String key = "java:counter:user:1";
        Object value = redisTemplate.opsForValue()
                .get(key);
        assertThat(value).isEqualTo(2L);
    }

    @Test
    @Order(200)
    void incrementForJdkSerialization() {
        String key = "java:counter:user:1";
        Exception exception = catchException(() ->
                redisTemplate.opsForValue()
                        .increment(key, 1L)
        );

        assertThat(exception)
                .isInstanceOf(RedisSystemException.class);
        assertThat(exception.getMessage())
                .isEqualTo("Error in execution; nested exception is io.lettuce.core.RedisCommandExecutionException: ERR value is not an integer or out of range");
    }

    /**
     * <pre>
     * 序列化程序
     * RedisSerializer.string()
     * key = "string:counter:user:3"
     * value = "4"
     * </pre>
     */
    @Test
    @Order(1)
    void setForString() {
        String key = "string:counter:user:3";
        String value = "4";
        stringRedisTemplate.opsForValue()
                .set(key, value, CACHE_TIMEOUT);
    }

    @Test
    @Order(101)
    void getForString() {
        String key = "string:counter:user:3";
        String value = stringRedisTemplate.opsForValue()
                .get(key);
        assertThat(value).isEqualTo("4");
    }

    /**
     * <pre>
     * 序列化程序
     * RedisSerializer.string()
     * key = "string:counter:user:3"
     * value = "6"
     * </pre>
     */
    @Test
    @Order(201)
    void incrementForString() {
        String key = "string:counter:user:3";
        Long increment = stringRedisTemplate.opsForValue()
                .increment(key, 2L);
        assertThat(increment).isEqualTo(6L);
    }
}
