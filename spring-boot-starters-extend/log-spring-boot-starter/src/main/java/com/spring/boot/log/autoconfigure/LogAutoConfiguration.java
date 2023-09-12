package com.spring.boot.log.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

/**
 * Auto-configuration for Log.
 *
 * @author guang.yi
 * @since 2023/9/11
 */
@Slf4j
@AutoConfiguration
public class LogAutoConfiguration {

    public LogAutoConfiguration() {
        log.info("create LogAutoConfiguration");
    }

    @Bean
    @ConditionalOnMissingBean
    public LogApplicationRunner logApplicationRunner() {
        return new LogApplicationRunner();
    }

    @Bean
    @ConditionalOnMissingBean
    public LogCommandLineRunner logCommandLineRunner() {
        return new LogCommandLineRunner();
    }

}
