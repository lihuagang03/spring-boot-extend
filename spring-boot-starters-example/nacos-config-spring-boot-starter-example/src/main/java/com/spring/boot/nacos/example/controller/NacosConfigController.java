package com.spring.boot.nacos.example.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.spring.boot.nacos.example.config.ExampleNacosConfigListener;
import com.spring.boot.nacos.example.config.ExampleNacosConfigurationProperties;
import com.spring.boot.nacos.example.service.ConfigServiceDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * nacos配置控制器
 *
 * @author guangyi
 * @date 2023/7/19
 */
@Slf4j
@RequestMapping("/config/nacos")
@RestController("configServiceController")
public class NacosConfigController {

    private final ConfigServiceDemo configServiceDemo;

    /**
     * 配置属性值
     *
     * @see NacosValue
     */
    @NacosValue(value = "${dynamic.config.enable:}", autoRefreshed = true)
    private String dynamicConfigEnable;

    /**
     * 配置属性集
     */
    private final ExampleNacosConfigurationProperties exampleNacosConfigurationProperties;

    /**
     * 配置属性
     */
    private final ExampleNacosConfigListener exampleNacosConfigListener;

    public NacosConfigController(
            ConfigServiceDemo configServiceDemo,
            ExampleNacosConfigurationProperties exampleNacosConfigurationProperties,
            ExampleNacosConfigListener exampleNacosConfigListener
    ) {
        this.configServiceDemo = configServiceDemo;
        this.exampleNacosConfigurationProperties = exampleNacosConfigurationProperties;
        this.exampleNacosConfigListener = exampleNacosConfigListener;
        log.info("create NacosConfigController instance");
    }

    @GetMapping("")
    public final String getConfig() {
        return configServiceDemo.getConfig();
    }

    @GetMapping("/value")
    public final String getConfigValue() {
        return dynamicConfigEnable;
    }

    @GetMapping("/properties")
    public final ExampleNacosConfigurationProperties getConfigProperties() {
        return exampleNacosConfigurationProperties;
    }

    @GetMapping("/value/listener")
    public final String getConfigValueListener() {
        return exampleNacosConfigListener.getCount();
    }
}
