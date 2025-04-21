package com.spring.boot.log.event;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.context.event.EventListener;

import lombok.extern.slf4j.Slf4j;

/**
 * Liveness State of Kubernetes Probes.
 * <p>
 * 7.1.6. Application Availability
 * <a href="https://kubernetes.io/docs/tasks/configure-pod-container/configure-liveness-readiness-startup-probes/">
 *     Kubernetes Probes</a>
 * Spring Boot includes out-of-the box support for the commonly used “liveness” and “readiness” availability states.
 * <p>
 * Managing the Application Availability State
 *
 * @see org.springframework.boot.availability.AvailabilityChangeEvent
 * @see org.springframework.boot.availability.ReadinessState
 * @see org.springframework.boot.availability.LivenessState
 */
@Slf4j
public class LivenessStateEventListener {
    public LivenessStateEventListener() {
        log.info("create LivenessStateEventListener");
    }

    @EventListener
    public void onStateChangeEvent(AvailabilityChangeEvent<LivenessState> event) {
        switch (event.getState()) {
            case CORRECT:
                log.info("receive event CORRECT");
                break;
            case BROKEN:
                log.info("receive event BROKEN");
                break;
            default:
                log.info("receive event default");
                break;
        }
    }
}
