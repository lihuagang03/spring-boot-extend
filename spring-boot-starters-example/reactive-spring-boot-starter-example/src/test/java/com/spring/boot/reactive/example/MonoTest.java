package com.spring.boot.reactive.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * Test of {@link Mono}.
 * <p>
 * 4. Reactor Core Features
 * https://projectreactor.io/docs/core/release/reference/index.html#core-features
 * <p>
 * https://github.com/reactor/reactor-core/blob/main/reactor-core/src/test/java/reactor/guide/GuideTests.java
 *
 * @author guangyi
 * @since 2023/7/25
 */
class MonoTest {

    /**
     * 4.3. Simple Ways to Create a Flux or Mono and Subscribe to It
     */
    @Test
    void createMono() {
        Mono<String> noData = Mono.empty();

        StepVerifier.create(noData)
                .expectComplete()
                .verify()
        ;

        Mono<String> data = Mono.just("foo");

        StepVerifier.create(data)
                .expectNext("foo")
                .verifyComplete()
        ;
    }
}
