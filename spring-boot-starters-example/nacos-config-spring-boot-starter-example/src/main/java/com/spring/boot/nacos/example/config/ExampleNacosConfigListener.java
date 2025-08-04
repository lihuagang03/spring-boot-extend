package com.spring.boot.nacos.example.config;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * 配置属性
 *
 * @since 2023/7/21
 * @see NacosValue
 * @see NacosConfigListener
 */
@Slf4j
@Data
@Configuration(value = "exampleNacosConfigListener", proxyBeanMethods = false)
public class ExampleNacosConfigListener {

    /**
     * 配置属性值
     */
    @NacosValue(value = "${people.count:0}", autoRefreshed = true)
    private String count;

    public ExampleNacosConfigListener() {
        log.info("create ExampleNacosConfigListener");
    }

    /**
     * 配置监视器
     *
     * @see NacosConfigListener
     */
    @NacosConfigListener(
            groupId = "${nacos.config.group:}",
            dataId = "${nacos.config.data-id:}",
            type = ConfigType.YAML,
            timeout = 3_000L
    )
    public final void onChange(String newContent) {
        log.info("config onChange,newContent={}", newContent);
    }

}
