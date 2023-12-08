package com.spring.boot.nacos.example.config;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * 配置属性集
 *
 * @author guang.yi
 * @since 2023/7/21
 * @see NacosConfigurationProperties
 */
@Slf4j
@Data
@NacosConfigurationProperties(
        groupId = "${nacos.config.group:}",
        dataId = "${nacos.config.data-id:}",
        type = ConfigType.YAML,
        autoRefreshed = true
)
@Configuration(value = "exampleNacosConfigurationProperties", proxyBeanMethods = false)
public class ExampleNacosConfigurationProperties {

    private String dynamicConfig;

    private String configNamespace;

    public ExampleNacosConfigurationProperties() {
        log.info("create ExampleNacosConfigurationProperties");
    }

}
