package com.spring.boot.log;

import javax.annotation.PostConstruct;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.context.ApplicationEventPublisher;

import com.spring.boot.log.exception.CacheCompletelyBrokenException;
import lombok.extern.slf4j.Slf4j;

/**
 * 本地缓存验证程序
 */
@Slf4j
public class LocalCacheVerifier {
    /**
     * 应用事件发布者
     */
    private final ApplicationEventPublisher applicationEventPublisher;

    public LocalCacheVerifier(
            ApplicationEventPublisher applicationEventPublisher
    ) {
        this.applicationEventPublisher = applicationEventPublisher;
        log.info("create LocalCacheVerifier");
    }

    @PostConstruct
    public void checkLocalCache() {
        log.info("check LocalCache");
        try {
            // ...
        } catch (CacheCompletelyBrokenException ex) {
            AvailabilityChangeEvent.publish(this.applicationEventPublisher,
                    ex, LivenessState.BROKEN);
        }
    }
}
