package com.spring.boot.reactive.web.flux;

import com.spring.boot.reactive.web.flux.entity.Greeting;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

/**
 * Create a WebFlux Handler
 * WebFlux处理器
 *
 * @since 2023/8/13
 * @see HandlerFunction
 * @see ServerRequest
 * @see ServerResponse
 */
@Component("greetingHandler")
public class GreetingHandler {

    /**
     * 请求-响应处理
     * <pre>
     * <a href="http://localhost:8386/hello">http://localhost:8386/hello</a>
     *
     * {"message":"Hello, Spring!"}
     * </pre>
     *
     * @param request 请求信息
     * @return 响应信息
     * @see HandlerFunction#handle(ServerRequest)
     * @see BodyInserter
     * @see BodyInserters
     */
    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("Hello, Spring!")))
                ;
    }
}
