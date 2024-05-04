package com.spring.boot.reactive.example;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 * 示例订阅者
 *
 * @since 2023/7/27
 */
public class SampleSubscriber<T> extends BaseSubscriber<T> {
    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        super.request(1L);
//        subscription.request(1L);
    }

    @Override
    protected void hookOnNext(T value) {
        System.out.println(value);
        super.request(1L);
//        super.hookOnNext(value);
    }
}
