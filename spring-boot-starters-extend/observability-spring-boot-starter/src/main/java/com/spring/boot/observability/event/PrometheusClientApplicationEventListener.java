package com.spring.boot.observability.event;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import io.prometheus.client.hotspot.DefaultExports;
import lombok.extern.slf4j.Slf4j;

/**
 * Spring应用事件侦听器
 * <p>
 * 7.1.7. Application Events and Listeners
 *
 * @author guang.yi
 * @since 2023/9/18
 * @see org.springframework.boot.autoconfigure.BackgroundPreinitializer
 * @see org.springframework.context.ApplicationListener
 * @see org.springframework.boot.context.event.SpringApplicationEvent
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PrometheusClientApplicationEventListener implements
        ApplicationListener<SpringApplicationEvent> {

    private static final AtomicBoolean ENABLE = new AtomicBoolean(false);

    public PrometheusClientApplicationEventListener() {
        log.info("create PrometheusClientApplicationEventListener");
    }

    @Override
    public void onApplicationEvent(SpringApplicationEvent event) {
        if (event instanceof ApplicationStartedEvent) {
            // 5. An ApplicationStartedEvent is sent after the context has been refreshed
            // but before any application and command-line runners have been called.
            if (ENABLE.compareAndSet(false, true)) {
                log.info("init and register the default Hotspot collectors.");
                DefaultExports.initialize();
            }
        }
        // 6. An AvailabilityChangeEvent is sent right after with LivenessState.CORRECT
        // to indicate that the application is considered as live.
        else if (event instanceof ApplicationReadyEvent) {
            // 7. An ApplicationReadyEvent is sent after any application
            // and command-line runners have been called.
        }
        // 8. An AvailabilityChangeEvent is sent right after with ReadinessState.ACCEPTING_TRAFFIC
        // to indicate that the application is ready to service requests.
    }

}
