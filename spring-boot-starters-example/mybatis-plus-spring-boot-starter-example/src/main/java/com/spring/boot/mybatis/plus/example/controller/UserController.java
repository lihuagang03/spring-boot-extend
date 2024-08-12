package com.spring.boot.mybatis.plus.example.controller;

import javax.annotation.Resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.mybatis.plus.example.repository.entity.User;
import com.spring.boot.mybatis.plus.example.service.UserService;

/**
 * 用户控制器
 *
 * @since 2024/8/9
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping(path = "/list")
    public List<User> listUser() {
        return userService.list();
    }
}
