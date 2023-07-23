package com.spring.boot.nacos.example.service;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 配置服务实现
 *
 * @author guangyi
 * @date 2023/7/19
 * @see NacosInjected
 */
@Slf4j
@Service("configServiceDemo")
public class ConfigServiceDemo {

    /**
     * 配置服务
     *
     * @see NacosInjected
     */
    @NacosInjected
    private ConfigService configService;

    @Value("${nacos.config.group}")
    private String group;
    @Value("${nacos.config.data-id}")
    private String dataId;

    // 构造器方法自动注入，不可行
//    private final ConfigService configService;
//
//    @NacosInjected
//    public ConfigServiceDemo(
//            ConfigService configService
//    ) {
//        this.configService = configService;
//    }


    public ConfigServiceDemo() {
        log.info("create ConfigServiceDemo");
    }

    public final String getConfig() {
        try {
            String content = configService.getConfig(dataId, group, 5_000L);
            log.info("get config, content={}", content);
            return content;
        } catch (NacosException e) {
            log.error("get config content fail,dataId={},group={}", dataId, group, e);
        }
        return null;
    }
}
