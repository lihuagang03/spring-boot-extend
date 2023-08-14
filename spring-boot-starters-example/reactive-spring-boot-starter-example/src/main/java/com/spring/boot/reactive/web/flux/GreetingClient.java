package com.spring.boot.reactive.web.flux;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import com.spring.boot.reactive.web.flux.entity.Greeting;

/**
 * Create a WebClient
 * Web客户端
 *
 * @author guang.yi
 * @since 2023/8/14
 * @see WebClient
 */
@Component("greetingClient")
public class GreetingClient {

    private final WebClient client;

    public GreetingClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:8080")
                .build();
    }

    public Mono<String> getMessage() {
        return this.client.get()
                .uri("/hello")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Greeting.class)
                .map(Greeting::getMessage)
                ;
    }
}
