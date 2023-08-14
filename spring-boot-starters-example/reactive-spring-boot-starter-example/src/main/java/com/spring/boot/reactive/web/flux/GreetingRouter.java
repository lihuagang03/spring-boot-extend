package com.spring.boot.reactive.web.flux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
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
 * @see RouterFunction
 * @see RouterFunctions
 * @see RequestPredicate
 * @see RequestPredicates
 */
@Configuration(proxyBeanMethods = false)
public class GreetingRouter {

    /**
     * <pre>
     * <a href="http://localhost:8080/hello">http://localhost:8080/hello</a>
     *
     * {"message":"Hello, Spring!"}
     * </pre>
     *
     * @return 响应信息的路由函数
     */
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
