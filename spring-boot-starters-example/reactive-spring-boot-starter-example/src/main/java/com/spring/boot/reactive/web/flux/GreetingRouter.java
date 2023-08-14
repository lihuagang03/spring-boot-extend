package com.spring.boot.reactive.web.flux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * Create a Router
 * 路由器
 *
 * @author guang.yi
 * @since 2023/8/13
 */
@Configuration(proxyBeanMethods = false)
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(
            GreetingHandler greetingHandler
    ) {
        return RouterFunctions.route(
                GET("/hello")
                        .and(accept(MediaType.APPLICATION_JSON)),
                greetingHandler::hello);
    }
}
