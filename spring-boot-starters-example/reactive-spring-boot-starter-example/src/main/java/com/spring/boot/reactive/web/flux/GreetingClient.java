package com.spring.boot.reactive.web.flux;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import com.spring.boot.reactive.web.flux.entity.Greeting;

/**
 * Create a WebClient
 * Web客户端
 *
 * @since 2023/8/14
 * @see WebClient
 */
@Component("greetingClient")
public class GreetingClient {
    private final WebClient client;

    public GreetingClient(
            WebClient.Builder webClientBuilder,
            ServerProperties serverProperties
    ) {
        String baseUrl = "http://localhost:" + serverProperties.getPort();
        this.client = webClientBuilder.baseUrl(baseUrl)
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
