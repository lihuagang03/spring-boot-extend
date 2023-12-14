package com.spring.boot.reactive.web.flux.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import lombok.extern.slf4j.Slf4j;

/**
 * <a href="https://docs.spring.io/spring-framework/reference/web/webflux/config.html">
 *     WebFlux Config</a>
 *
 * @author guang.yi
 * @since 2023/12/14
 */
@Slf4j
@EnableWebFlux
@Configuration(proxyBeanMethods = false)
public class WebFluxConfig implements WebFluxConfigurer {
    //

    public WebFluxConfig() {
        log.info("create WebFluxConfig");
    }

}
