package com.spring.boot.redis.example.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.spring.boot.redis.example.model.UserDto;
import com.spring.boot.redis.example.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户服务实现
 *
 * @since 2023/11/27
 */
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
        log.info("create UserServiceImpl");
    }

    @Cacheable(cacheNames = "user.info", key = "#userId")
    @Override
    public UserDto getById(long userId) {
        log.info("getById, userId={}", userId);
        return new UserDto()
                .setId(userId)
                .setPhone("13666555888")
                .setNickName("李四");
    }

}
