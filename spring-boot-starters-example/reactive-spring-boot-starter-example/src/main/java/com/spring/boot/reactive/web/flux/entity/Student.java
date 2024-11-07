package com.spring.boot.reactive.web.flux.entity;

import com.spring.boot.reactive.web.flux.model.Gender;
import org.springframework.data.redis.core.RedisHash;

/**
 * 学生【实体】
 *
 * @author lihuagang
 */
@RedisHash(value = "student", timeToLive = 60 * 60 * 24)
public record Student(String id, String name, Gender gender, int grade) {
}
