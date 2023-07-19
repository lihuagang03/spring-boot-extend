package com.spring.boot.nacos.example.controller;

import com.spring.boot.nacos.example.service.ConfigServiceDemo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置控制器
 *
 * @author guangyi
 * @date 2023/7/19
 */
@RestController("configServiceController")
@RequestMapping("/config")
public class ConfigController {

    private final ConfigServiceDemo configServiceDemo;

    public ConfigController(
            ConfigServiceDemo configServiceDemo
    ) {
        this.configServiceDemo = configServiceDemo;
    }

    @GetMapping("/nacos")
    public String getNacosConfig() {
        return configServiceDemo.getConfig();
    }
}
