package com.spring.boot.reactive.example;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * Test of {@link Flux}.
 *
 * @author guangyi
 * @date 2023/7/25
 */
class FluxTest {

    @Test
    void createFlux() {
        Flux<String> seq = Flux.just("foo", "bar", "foobar");

        StepVerifier.create(seq)
                .expectNext("foo")
                .expectNext("bar")
                .expectNext("foobar")
                .expectComplete()
                .verify()
        ;
    }

    @Test
    void testAppendBoomError() {
        Flux<String> source = Flux.just("thing1", "thing2");

        StepVerifier.create(appendBoomError(source))
                .expectNext("thing1")
                .expectNext("thing2")
                .expectErrorMessage("boom")
                .verify()
        ;
    }

    /**
     * 6.1. Testing a Scenario with StepVerifier
     * https://projectreactor.io/docs/core/release/reference/index.html#_testing_a_scenario_with_stepverifier
     */
    private static <T> Flux<T> appendBoomError(Flux<T> source) {
        return source.concatWith(Mono.error(new IllegalArgumentException("boom")));
    }
}
