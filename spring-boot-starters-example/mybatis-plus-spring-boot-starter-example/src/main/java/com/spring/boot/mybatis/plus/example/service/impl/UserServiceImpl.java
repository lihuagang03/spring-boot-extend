package com.spring.boot.mybatis.plus.example.service.impl;

import com.spring.boot.mybatis.plus.example.repository.entity.User;
import com.spring.boot.mybatis.plus.example.repository.mapper.UserMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.boot.mybatis.plus.example.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户实体服务实现
 *
 * @since 2023/6/10
 * @see com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    //
}
