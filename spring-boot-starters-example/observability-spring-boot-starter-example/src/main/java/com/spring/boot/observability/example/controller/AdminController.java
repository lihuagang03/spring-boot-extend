package com.spring.boot.observability.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 管理员控制器
 *
 * @since 2023/12/8
 */
@Slf4j
@RestController
@RequestMapping(path = "/admin")
public class AdminController {
    /**
     * 健康检查
     */
    @GetMapping(path = "/health/check")
    public String checkHealth() {
        log.info("checkHealth");
        return "OK";
    }
}
