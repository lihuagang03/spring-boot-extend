package com.spring.boot.reactive.example;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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
 * <p>
 * https://github.com/reactor/reactor-core/blob/main/reactor-core/src/test/java/reactor/guide/GuideTests.java
 *
 * @since 2023/7/25
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
                .verifyComplete()
        ;

        Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3);
        StepVerifier.create(numbersFromFiveToSeven)
                .expectNext(5, 6, 7)
                .verifyComplete()
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
     * 4.4.1. Synchronous: generate
     * 同步：生成
     * <p>
     * This is for synchronous and one-by-one emissions,
     * meaning that the sink is a SynchronousSink and that its next() method can only be called at most once per callback invocation.
     * <p>
     * The most useful variant is probably the one that also lets you keep a state that you can refer to in your sink usage to decide what to emit next.
     * The generator function then becomes a BiFunction<S, SynchronousSink<T>, S>, with <S> the type of the state object.
     * You have to provide a Supplier<S> for the initial state, and your generator function now returns a new state on each round.
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
        StepVerifier.create(flux)
                .expectNext("3 x 0 = 0")
                .expectNext("3 x 1 = 3")
                .expectNext("3 x 2 = 6")
                .expectNext("3 x 3 = 9")
                .expectNext("3 x 4 = 12")
                .expectNext("3 x 5 = 15")
                .expectNext("3 x 6 = 18")
                .expectNext("3 x 7 = 21")
                .expectNext("3 x 8 = 24")
                .expectNext("3 x 9 = 27")
                .expectNext("3 x 10 = 30")
                .verifyComplete()
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
        StepVerifier.create(flux)
                .expectNext("3 x 0 = 0")
                .expectNext("3 x 1 = 3")
                .expectNext("3 x 2 = 6")
                .expectNext("3 x 3 = 9")
                .expectNext("3 x 4 = 12")
                .expectNext("3 x 5 = 15")
                .expectNext("3 x 6 = 18")
                .expectNext("3 x 7 = 21")
                .expectNext("3 x 8 = 24")
                .expectNext("3 x 9 = 27")
                .expectNext("3 x 10 = 30")
                .verifyComplete()
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
        StepVerifier.create(flux)
                .expectNext("3 x 0 = 0")
                .expectNext("3 x 1 = 3")
                .expectNext("3 x 2 = 6")
                .expectNext("3 x 3 = 9")
                .expectNext("3 x 4 = 12")
                .expectNext("3 x 5 = 15")
                .expectNext("3 x 6 = 18")
                .expectNext("3 x 7 = 21")
                .expectNext("3 x 8 = 24")
                .expectNext("3 x 9 = 27")
                .expectNext("3 x 10 = 30")
                .verifyComplete()
        ;
    }

    private final MyEventProcessor myEventProcessor = new MyEventProcessor() {

        private MyEventListener<String> eventListener;
        private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        @Override
        public void register(MyEventListener<String> eventListener) {
            this.eventListener = eventListener;
        }

        @Override
        public void dataChunk(List<String> values) {
            executor.schedule(
                    () -> eventListener.onDataChunk(values),
                    500L, TimeUnit.MILLISECONDS
            );
        }

        @Override
        public void processComplete() {
            executor.schedule(
                    () -> eventListener.processComplete(),
                    500L, TimeUnit.MILLISECONDS
            );
        }

        @Override
        public void processError(Throwable e) {
            if (eventListener instanceof SingleThreadEventListener) {
                SingleThreadEventListener<?> listener = (SingleThreadEventListener<?>) eventListener;
//                executor.execute(() -> listener.processError(e));
                listener.processError(e);
            }
        }
    };

    /**
     * 4.4.2. Asynchronous and Multi-threaded: create
     * 异步和多线程：创建
     * <p>
     * create is a more advanced form of programmatic creation of a Flux which is suitable for multiple emissions per round,
     * even from multiple threads.
     * <p>
     * It exposes a FluxSink, with its next, error, and complete methods.
     * Contrary to generate, it does not have a state-based variant.
     * On the other hand, it can trigger multi-threaded events in the callback.
     * <p>
     * create can be very useful to bridge an existing API with the reactive world - such as an asynchronous API based on listeners.
     * <p>
     * create does not parallelize your code nor does it make it asynchronous, even though it can be used with asynchronous APIs.
     * If you block within the create lambda, you expose yourself to deadlocks and similar side effects.
     */
    @Test
    void createAsynchronousSequence() {
        // You can use create to bridge this into a Flux<T>:
        Flux<String> bridge = Flux.create(
                // All of this is done asynchronously whenever the myEventProcessor executes.
                fluxSink -> myEventProcessor.register(
                        // Bridge to the MyEventListener API
                        new MyEventListener<>() {

                            @Override
                            public void onDataChunk(List<String> chunk) {
                                for (String s : chunk) {
                                    // Each element in a chunk becomes an element in the Flux.
                                    fluxSink.next(s);
                                }
//                                chunk.forEach(fluxSink::next);
                            }

                            @Override
                            public void processComplete() {
                                // 	The processComplete event is translated to onComplete.
                                fluxSink.complete();
                            }
                        }
                )
        );
        // 空的输出，异步还没有触发数据推送
//        bridge.subscribe(System.out::println);

        StepVerifier.withVirtualTime(() -> bridge)
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(10L))
                .then(() -> myEventProcessor.dataChunk(Arrays.asList("foo", "bar", "baz")))
                .expectNext("foo", "bar", "baz")
                .expectNoEvent(Duration.ofSeconds(10L))
                .then(myEventProcessor::processComplete)
                .verifyComplete()
        ;
    }

    /**
     * 4.4.3. Asynchronous but single-threaded: push
     * 异步但是单线程：推送
     * <p>
     * push is a middle ground between generate and create which is suitable for processing events from a single producer.
     * It is similar to create in the sense that it can also be asynchronous and can manage backpressure using any of the overflow strategies supported by create.
     * However, only one producing thread may invoke next, complete or error at a time.
     */
    @Test
    void pushAsynchronousSequence() {
        Flux<String> bridge = Flux.push(
                fluxSink -> myEventProcessor.register(
                        // Bridge to the SingleThreadEventListener API.
                        new SingleThreadEventListener<>() {
                            @Override
                            public void onDataChunk(List<String> chunk) {
                                for (String s : chunk) {
                                    // Events are pushed to the sink using next from a single listener thread.
                                    fluxSink.next(s);
                                }
                            }

                            @Override
                            public void processComplete() {
                                // complete event generated from the same listener thread.
                                fluxSink.complete();
                            }

                            @Override
                            public void processError(Throwable e) {
                                // error event also generated from the same listener thread.
                                fluxSink.error(e);
                            }
                        }
                )
        );

        StepVerifier.withVirtualTime(() -> bridge)
                .expectSubscription()
                .then(() -> myEventProcessor.dataChunk(Arrays.asList("foo", "bar", "baz")))
                .expectNext("foo", "bar", "baz")
                .then(myEventProcessor::processComplete)
//                .verifyComplete()
                .then(() -> myEventProcessor.processError(new RuntimeException("done")))
                .verifyErrorMessage("done")
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
