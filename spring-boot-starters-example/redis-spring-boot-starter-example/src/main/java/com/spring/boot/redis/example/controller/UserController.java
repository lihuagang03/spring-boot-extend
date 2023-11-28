package com.spring.boot.redis.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.redis.example.model.UserDto;
import com.spring.boot.redis.example.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户控制器
 *
 * @author guang.yi
 * @since 2023/11/27
 */
@Slf4j
@RestController("userController")
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping("/user/{userId}")
    public UserDto index(@PathVariable("userId") Long userId) {
        UserDto userDto = userService.getById(userId);
        log.info("userDto={}", userDto);
        return userDto;
    }

}
