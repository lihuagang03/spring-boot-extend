package com.tencent.qq.weixin.autoconfigure;

import org.springframework.context.annotation.Configuration;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.core.env.StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME;
import static org.springframework.core.env.StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME;

/**
 * nacos配置属性源
 *
 * @since 2024/5/4
 * @see EnableNacosConfig
 * @see NacosPropertySource
 */
@Slf4j
@EnableNacosConfig
@NacosPropertySource(
        name = "dynamicConfig",
        groupId = "${nacos.config.group:}",
        dataId = "${nacos.config.data-id:}",
        autoRefreshed = true,
        first = true,
        before = SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME,
        after = SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME,
        type = ConfigType.YAML
)
@Configuration(proxyBeanMethods = false)
public class NacosConfiguration {
    public NacosConfiguration() {
        log.info("create NacosConfiguration");
    }
}
