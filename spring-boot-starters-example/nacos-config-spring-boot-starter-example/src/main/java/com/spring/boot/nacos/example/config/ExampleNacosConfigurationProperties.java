package com.spring.boot.nacos.example.config;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * 配置属性集
 *
 * @author guangyi
 * @date 2023/7/21
 * @see NacosConfigurationProperties
 */
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
}
