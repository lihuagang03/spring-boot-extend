package com.tencent.qq.weixin.autoconfigure;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 配置属性集
 *
 * @since 2024/5/4
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
@Configuration(proxyBeanMethods = false)
public class AppNacosConfigurationProperties implements InitializingBean {
    /**
     * 账号映射表
     * <pre>
     * {@code <appId, AppProperties>}
     * </pre>
     */
    private Map<String, AppProperties> appPropertiesMap;

    public AppNacosConfigurationProperties() {
        log.info("create AppNacosConfigurationProperties");
    }

    @Override
    public void afterPropertiesSet() {
        log.info("appPropertiesMap={}", appPropertiesMap);
    }
}
