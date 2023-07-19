package com.spring.boot.nacos.example.service;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.stereotype.Service;

/**
 * 配置服务实现
 *
 * @author guangyi
 * @date 2023/7/19
 */
@Service("configServiceDemo")
public class ConfigServiceDemo {

    @NacosInjected
    private ConfigService configService;

    // 构造器方法自动注入，不可行
//    private final ConfigService configService;
//
//    @NacosInjected
//    public ConfigServiceDemo(
//            ConfigService configService
//    ) {
//        this.configService = configService;
//    }

    public String getConfig() {
        String dataId = "nacos-config-spring-boot-starter-example";
        String group = "DEFAULT_GROUP";
        try {
            String content = configService.getConfig(dataId, group, 5_000L);
            System.out.println(content);
            return content;
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }
}
