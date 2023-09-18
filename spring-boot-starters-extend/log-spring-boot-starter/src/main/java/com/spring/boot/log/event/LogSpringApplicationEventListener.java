package com.spring.boot.log.event;

import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationListener;

import lombok.extern.slf4j.Slf4j;

/**
 * Spring应用事件侦听器
 * <p>
 * 7.1.7. Application Events and Listeners
 *
 * @author guang.yi
 * @since 2023/9/13
 * @see org.springframework.context.ApplicationListener
 * @see org.springframework.boot.context.event.SpringApplicationEvent
 * @see org.springframework.boot.autoconfigure.BackgroundPreinitializer
 */
@Slf4j
public class LogSpringApplicationEventListener implements
        ApplicationListener<SpringApplicationEvent> {

    public LogSpringApplicationEventListener() {
        log.info("create LogSpringApplicationEventListener");
    }

    @Override
    public void onApplicationEvent(SpringApplicationEvent event) {
        log.info("receive event {}", event);
        if (event instanceof ApplicationStartingEvent) {
            // 1. An ApplicationStartingEvent is sent at the start of a run but before any processing,
            // except for the registration of listeners and initializers.
        }
        else if (event instanceof ApplicationEnvironmentPreparedEvent) {
            // 2. An ApplicationEnvironmentPreparedEvent is sent when the Environment to be used in
            // the context is known but before the context is created.
        }
        else if (event instanceof ApplicationContextInitializedEvent) {
            // 3. An ApplicationContextInitializedEvent is sent when the ApplicationContext is prepared
            // and ApplicationContextInitializers have been called but before any bean definitions are loaded.
        }
        else if (event instanceof ApplicationPreparedEvent) {
            // 4. An ApplicationPreparedEvent is sent just before the refresh is started
            // but after bean definitions have been loaded.
        }
        else if (event instanceof ApplicationStartedEvent) {
            // 5. An ApplicationStartedEvent is sent after the context has been refreshed
            // but before any application and command-line runners have been called.
        }
        // 6. An AvailabilityChangeEvent is sent right after with LivenessState.CORRECT
        // to indicate that the application is considered as live.
        else if (event instanceof ApplicationReadyEvent) {
            // 7. An ApplicationReadyEvent is sent after any application
            // and command-line runners have been called.
        }
        // 8. An AvailabilityChangeEvent is sent right after with ReadinessState.ACCEPTING_TRAFFIC
        // to indicate that the application is ready to service requests.
        else if (event instanceof ApplicationFailedEvent) {
            // 9. An ApplicationFailedEvent is sent if there is an exception on startup.
        }
    }

}
