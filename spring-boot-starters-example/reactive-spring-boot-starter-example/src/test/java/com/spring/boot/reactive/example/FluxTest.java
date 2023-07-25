package com.spring.boot.reactive.example;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Test of {@link Flux}.
 *
 * @author guangyi
 * @date 2023/7/25
 */
class FluxTest {

    /**
     * 4.3. Simple Ways to Create a Flux or Mono and Subscribe to It
     */
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

        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        seq = Flux.fromIterable(iterable);

        StepVerifier.create(seq)
                .expectNext("foo")
                .expectNext("bar")
                .expectNext("foobar")
                .expectComplete()
                .verify()
        ;

        Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3);
        StepVerifier.create(numbersFromFiveToSeven)
                .expectNext(5, 6, 7)
                .expectComplete()
                .verify()
        ;
    }

    /**
     * 4.3.1. subscribe Method Examples
     */
    @Test
    void subscribe() {
        Flux<Integer> integers = Flux.range(1, 3);
        // The preceding code produces no visible output, but it does work.
        // The Flux produces three values.
        integers.subscribe();
        // If we provide a lambda, we can make the values visible.
        integers.subscribe(System.out::println);

        // To demonstrate the next signature, we intentionally introduce an error
        integers = Flux.range(1, 4)
                .map(integer -> {
                    if (integer <= 3) {
                        return integer;
                    }
                    throw new RuntimeException("Got to 4");
                });
        integers.subscribe(System.out::println,
                error -> System.err.println("Error: " + error));

        // The next signature of the subscribe method includes both an error handler and a handler for completion events
        integers = Flux.range(1, 4);
        integers.subscribe(System.out::println,
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Done"));
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
        return source.concatWith(Flux.error(new IllegalArgumentException("boom")));
    }
}
