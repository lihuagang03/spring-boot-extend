package com.spring.boot.redis.example.service;

import com.spring.boot.redis.example.model.UserDto;

/**
 * 用户服务
 *
 * @author guang.yi
 * @since 2023/11/27
 */
public interface UserService {

    /**
     * 通过用户id查询用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    UserDto getById(long userId);

}
