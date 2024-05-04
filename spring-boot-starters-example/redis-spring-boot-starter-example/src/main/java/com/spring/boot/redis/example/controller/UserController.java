package com.spring.boot.redis.example.controller;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.redis.example.model.CacheKey;
import com.spring.boot.redis.example.model.UserDto;
import com.spring.boot.redis.example.service.CacheService;
import com.spring.boot.redis.example.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户控制器
 *
 * @since 2023/11/27
 */
@Slf4j
@Validated
@RestController("userController")
public class UserController {

    private final UserService userService;

    private final CacheService cacheService;

    public UserController(
            UserService userService,
            CacheService cacheService
    ) {
        this.userService = userService;
        this.cacheService = cacheService;
    }

    @GetMapping("/user/{userId}")
    public UserDto index(@PathVariable("userId") Long userId) {
        UserDto userDto = userService.getById(userId);
        log.info("userDto={}", userDto);
        return userDto;
    }

    /**
     * 清理Redis缓存的键模式
     * <pre>
     * cleanCache end, totalTimeMillis=24
     * </pre>
     *
     * @param cacheKey 缓存的键
     * @return 操作结果
     */
    @PostMapping("/cache/clean")
    @ResponseBody
    public String cleanCache(@Valid @RequestBody CacheKey cacheKey) {
        cacheService.cleanCache(cacheKey);

        return "{\"code\":0,\"message\":\"ok\"}";
    }

}
