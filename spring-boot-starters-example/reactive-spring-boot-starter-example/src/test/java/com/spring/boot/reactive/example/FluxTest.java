package com.spring.boot.reactive.example;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.*;

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
        System.out.println();

        // To demonstrate the next signature, we intentionally introduce an error
        integers = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) {
                        return i;
                    }
                    throw new RuntimeException("Got to 4");
                });
        integers.subscribe(System.out::println,
                error -> System.err.println("Error: " + error));
        System.out.println();

        // The next signature of the subscribe method includes both an error handler and a handler for completion events
        integers = Flux.range(1, 4);
        integers.subscribe(System.out::println,
                error -> System.err.println("Error: " + error),
                () -> System.out.println("Done"));
    }

    /**
     * 4.3.2. Cancelling a subscribe() with Its Disposable
     */
    @Test
    void dispose() {
        Flux<Integer> integers = Flux.range(1, 3);
        Disposable disposable = integers.subscribe(System.out::println);
        disposable.dispose();
        assertThat(disposable.isDisposed()).isTrue();
    }

    /**
     * 4.3.3. An Alternative to Lambdas: BaseSubscriber
     * <p>
     * There is an additional subscribe method that is more generic and takes a full-blown Subscriber rather than composing one out of lambdas.
     * In order to help with writing such a Subscriber, we provide an extendable class called BaseSubscriber.
     */
    @Test
    void baseSubscriber() {
        SampleSubscriber<Integer> baseSubscriber = new SampleSubscriber<>();
        Flux<Integer> flux = Flux.range(1, 4);
        flux.subscribe(baseSubscriber);
    }

    /**
     * 4.3.4. On Backpressure and Ways to Reshape Requests
     */
    @Test
    void onBackpressureReshapeRequests() {
        // The simplest way of customizing the original request is to subscribe with a BaseSubscriber with the hookOnSubscribe method overridden
        Flux.range(1, 10)
                .doOnRequest(r -> System.out.println("request of " + r))
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        super.request(1L);
//                        super.hookOnSubscribe(subscription);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("Cancelling after having received " + value);
                        super.cancel();
                    }
                });
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
