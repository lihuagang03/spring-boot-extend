package com.spring.boot.log.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

import com.spring.boot.log.LocalCacheVerifier;
import com.spring.boot.log.LogApplicationRunner;
import com.spring.boot.log.LogCommandLineRunner;
import com.spring.boot.log.event.LivenessStateEventListener;
import com.spring.boot.log.event.ReadinessStateEventListener;
import lombok.extern.slf4j.Slf4j;

/**
 * Auto-configuration for Log.
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

    @Bean
    @ConditionalOnMissingBean
    public ReadinessStateEventListener readinessStateEventListener() {
        return new ReadinessStateEventListener();
    }

    @Bean
    @ConditionalOnMissingBean
    public LivenessStateEventListener livenessStateEventListener() {
        return new LivenessStateEventListener();
    }

    @Bean
    @ConditionalOnMissingBean
    public LocalCacheVerifier localCacheVerifier(
            ApplicationEventPublisher applicationEventPublisher
    ) {
        return new LocalCacheVerifier(applicationEventPublisher);
    }
}
