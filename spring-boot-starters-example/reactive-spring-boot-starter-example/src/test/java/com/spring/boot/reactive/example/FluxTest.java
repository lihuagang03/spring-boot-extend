package com.spring.boot.reactive.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link Flux}.
 * <p>
 * 4. Reactor Core Features
 * https://projectreactor.io/docs/core/release/reference/index.html#core-features
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
        Flux<String> sequence = Flux.just("foo", "bar", "foobar");

        StepVerifier.create(sequence)
                .expectNext("foo")
                .expectNext("bar")
                .expectNext("foobar")
                .expectComplete()
                .verify()
        ;

        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        sequence = Flux.fromIterable(iterable);

        StepVerifier.create(sequence)
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
        // Lambda-based subscribe variants for Flux
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
     * 关于背压和重塑请求的方法
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

    /**
     * 4.4. Programmatically creating a sequence
     * <p>
     * In this section, we introduce the creation of a Flux or a Mono by programmatically defining its associated events (onNext, onError, and onComplete).
     * All these methods share the fact that they expose an API to trigger the events that we call a sink.
     * <p>
     * 4.4.1. Synchronous generate
     */
    @Test
    void generateSynchronousSequence() {
        // state-based generate
        Flux<String> flux = Flux.generate(
                // We supply the initial state value of 0.
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state);
                    if (state == 10) {
                        // We also use it to choose when to stop.
                        sink.complete();
                    }
                    // We return a new state that we use in the next invocation (unless the sequence terminated in this one).
                    return state + 1;
                }
        );
//        flux.subscribe(System.out::println);
//        System.out.println();
        StepVerifier.create(flux)
                .expectNext("3 x 0 = 0", "3 x 1 = 3", "3 x 2 = 6", "3 x 3 = 9", "3 x 4 = 12")
                .expectNext("3 x 5 = 15", "3 x 6 = 18", "3 x 7 = 21", "3 x 8 = 24", "3 x 9 = 27")
                .expectNext("3 x 10 = 30")
                .expectComplete()
                .verify()
        ;

        // Mutable state variant
        // 可变的状态变体
        flux = Flux.generate(
                // This time, we generate a mutable object as the state.
                AtomicLong::new,
                (state, sink) -> {
                    // We mutate the state here.
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10L) {
                        sink.complete();
                    }
                    // We return the same instance as the new state.
                    return state;
                }
        );
//        flux.subscribe(System.out::println);
//        System.out.println();
        StepVerifier.create(flux)
                .expectNext("3 x 0 = 0", "3 x 1 = 3", "3 x 2 = 6", "3 x 3 = 9", "3 x 4 = 12")
                .expectNext("3 x 5 = 15", "3 x 6 = 18", "3 x 7 = 21", "3 x 8 = 24", "3 x 9 = 27")
                .expectNext("3 x 10 = 30")
                .expectComplete()
                .verify()
        ;

        // uses the generate method that includes a Consumer
        flux = Flux.generate(
                // Again, we generate a mutable object as the state.
                AtomicLong::new,
                (state, sink) -> {
                    // We mutate the state here.
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10L) {
                        sink.complete();
                    }
                    // We return the same instance as the new state.
                    return state;
                },
                // We see the last state value (11) as the output of this Consumer lambda.
                (state) -> System.out.println("state: " + state)
                // In the case of the state containing a database connection or other resource that needs to be handled at the end of the process,
                // the Consumer lambda could close the connection or otherwise handle any tasks that should be done at the end of the process.
        );
//        flux.subscribe(System.out::println);
//        System.out.println();
        StepVerifier.create(flux)
                .expectNext("3 x 0 = 0", "3 x 1 = 3", "3 x 2 = 6", "3 x 3 = 9", "3 x 4 = 12")
                .expectNext("3 x 5 = 15", "3 x 6 = 18", "3 x 7 = 21", "3 x 8 = 24", "3 x 9 = 27")
                .expectNext("3 x 10 = 30")
                .expectComplete()
                .verify()
        ;
    }

    /**
     * 6.1. Testing a Scenario with StepVerifier
     */
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
