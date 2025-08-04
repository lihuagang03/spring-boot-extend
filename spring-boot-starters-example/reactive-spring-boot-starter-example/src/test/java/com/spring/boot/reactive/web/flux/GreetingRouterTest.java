package com.spring.boot.reactive.web.flux;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.spring.boot.reactive.web.flux.entity.Greeting;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link GreetingRouter}.
 * <p>
 * We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
 *
 * @since 2023/8/14
 * @see WebTestClient
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GreetingRouterTest {

    /**
     * Spring Boot will create a `WebTestClient` for you,
     * already configure and ready to issue requests against "localhost:RANDOM_PORT"
     */
    @Resource
    private WebTestClient webTestClient;

    @Test
    void hello() {
        // Create a GET request to test an endpoint
        webTestClient.get()
                .uri("/hello")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                // and use the dedicated DSL to test assertions against the response
                .expectStatus()
                .isOk()
                .expectBody(Greeting.class)
                .value(greeting -> assertThat(greeting.getMessage())
                        .isEqualTo("Hello, Spring!")
                )
        ;
    }
}
