package com.spring.boot.log;

import java.time.Duration;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import lombok.extern.slf4j.Slf4j;

/**
 * Spring应用运行侦听器
 *
 * @author guang.yi
 * @since 2023/9/13
 * @see org.springframework.boot.SpringApplicationRunListener
 * @see org.springframework.boot.context.event.EventPublishingRunListener
 */
@Slf4j
public class LogSpringApplicationRunListener implements SpringApplicationRunListener {

    private final SpringApplication application;

    private final String[] args;

    public LogSpringApplicationRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
        log.info("create LogSpringApplicationRunListener");
    }

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        SpringApplicationRunListener.super.starting(bootstrapContext);
        log.info("Called immediately when the run method has first started. " +
                "Can be used for very early initialization. " +
                "bootstrapContext={}", bootstrapContext);
    }

    @Override
    public void environmentPrepared(
            ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        SpringApplicationRunListener.super.environmentPrepared(bootstrapContext, environment);
        log.info("Called once the environment has been prepared, " +
                "but before the ApplicationContext has been created. " +
                "bootstrapContext={}, environment={}", bootstrapContext, environment);
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        SpringApplicationRunListener.super.contextPrepared(context);
        log.info("Called once the ApplicationContext has been created and prepared, " +
                "but before sources have been loaded. " +
                "context={}", context);
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        SpringApplicationRunListener.super.contextLoaded(context);
        log.info("Called once the application context has been loaded but before it has been refreshed. " +
                "context={}", context);
    }

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        SpringApplicationRunListener.super.started(context, timeTaken);
        log.info("The context has been refreshed and the application has started " +
                "but CommandLineRunners and ApplicationRunners have not been called. " +
                "context={}, timeTaken={}", context, timeTaken);
    }

    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        SpringApplicationRunListener.super.ready(context, timeTaken);
        log.info("Called immediately before the run method finishes, " +
                "when the application context has been refreshed and " +
                "all CommandLineRunners and ApplicationRunners have been called. " +
                "context={}, timeTaken={}", context, timeTaken);
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        SpringApplicationRunListener.super.failed(context, exception);
        log.info("Called when a failure occurs when running the application. " +
                "context={}", context, exception);
    }

}
