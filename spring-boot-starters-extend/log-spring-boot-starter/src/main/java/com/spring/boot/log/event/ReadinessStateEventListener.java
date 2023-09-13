package com.spring.boot.log.event;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.event.EventListener;

import lombok.extern.slf4j.Slf4j;

/**
 * Readiness State of Kubernetes Probes.
 * <p>
 * 7.1.6. Application Availability
 * <a href="https://kubernetes.io/docs/tasks/configure-pod-container/configure-liveness-readiness-startup-probes/">
 *     Kubernetes Probes</a>
 * Spring Boot includes out-of-the box support for the commonly used “liveness” and “readiness” availability states.
 * <p>
 * Managing the Application Availability State
 *
 * @author guang.yi
 * @since 2023/9/12
 * @see org.springframework.boot.availability.AvailabilityChangeEvent
 * @see org.springframework.boot.availability.ReadinessState
 * @see org.springframework.boot.availability.LivenessState
 */
@Slf4j
public class ReadinessStateEventListener {

    public ReadinessStateEventListener() {
        log.info("create ReadinessStateEventListener");
    }

    @EventListener
    public void onStateChangeEvent(AvailabilityChangeEvent<ReadinessState> event) {
        switch (event.getState()) {
            case ACCEPTING_TRAFFIC:
                log.info("receive event ACCEPTING_TRAFFIC");
                break;
            case REFUSING_TRAFFIC:
                log.info("receive event REFUSING_TRAFFIC");
                break;
            default:
                log.info("receive event default");
                break;
        }
    }

}
