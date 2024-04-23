package com.spring.boot.observability.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

import com.alibaba.fastjson.JSON;
import com.spring.boot.observability.example.model.UserModel;
import com.spring.boot.observability.example.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户控制器
 *
 * @since 2023/12/16
 */
@Slf4j
@RequestMapping(path = "/user")
@RestController("userController")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public Mono<UserModel> getById(@PathVariable("userId") Long userId) {
        Mono<UserModel> mono = userService.getById(userId);
        // mono=MonoJust
//        log.info("mono={}", mono);
        // mono={"scanAvailable":true}
//        log.info("mono={}", JSON.toJSONString(mono));
        return mono;
    }

}
