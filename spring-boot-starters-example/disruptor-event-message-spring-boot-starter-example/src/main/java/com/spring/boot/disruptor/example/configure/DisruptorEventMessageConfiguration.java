package com.spring.boot.disruptor.example.configure;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.spring.boot.disruptor.example.event.OrderEvent;
import com.spring.boot.disruptor.example.event.UserEvent;
import com.spring.boot.disruptor.example.event.factory.OrderEventFactory;
import com.spring.boot.disruptor.example.event.factory.UserEventFactory;
import com.spring.boot.disruptor.example.event.handler.OrderEventHandler;
import com.spring.boot.disruptor.example.event.handler.UserEventHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 消息队列配置
 *
 * @author lihuagang
 */
@Configuration
public class DisruptorEventMessageConfiguration {
    /**
     * <pre>
     * <a href="https://www.cnblogs.com/benwu/articles/16267820.html">Spring-Boot项目使用Disruptor做内部消息队列</a>
     *
     * <a href="https://lmax-exchange.github.io/disruptor/user-guide/">Disruptor User Guide</a>
     * </pre>
     */
    @Bean
    @ConditionalOnProperty(name = "user.event.enabled", matchIfMissing = true)
    public RingBuffer<UserEvent> userEventRingBuffer() {
        // 指定事件工厂
        UserEventFactory eventFactory = new UserEventFactory();

        // 指定RingBuffer字节大小，必须为2的N次方(能将求模运算转为位运算提高效率)，否则将影响效率
        int ringBufferSize = 1024 * 16;

        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        // 设置事件业务处理器---消费者
        UserEventHandler eventHandler = new UserEventHandler();

        return newRingBuffer(eventFactory, ringBufferSize, threadFactory, eventHandler);
    }

    @Bean
    @ConditionalOnProperty(name = "order.event.enabled", matchIfMissing = true)
    public RingBuffer<OrderEvent> orderEventRingBuffer() {
        // 指定事件工厂
        OrderEventFactory eventFactory = new OrderEventFactory();

        // 指定RingBuffer字节大小，必须为2的N次方(能将求模运算转为位运算提高效率)，否则将影响效率
        int ringBufferSize = 1024 * 16;

        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        // 设置事件业务处理器---消费者
        OrderEventHandler eventHandler = new OrderEventHandler();

        return newRingBuffer(eventFactory, ringBufferSize, threadFactory, eventHandler);
    }

    private <E> RingBuffer<E> newRingBuffer(
            final EventFactory<E> eventFactory,
            final int ringBufferSize,
            final ThreadFactory threadFactory,
            final EventHandler<E> eventHandler
    ) {
        // 定义用于事件处理的线程池，Disruptor通过ExecutorService提供的线程来触发消费者的事件处理

        // 指定事件工厂
//        EventFactory<E> eventFactory = new EventFactory<>();

        // 指定RingBuffer字节大小，必须为2的N次方(能将求模运算转为位运算提高效率)，否则将影响效率
//        int ringBufferSize = 1024 * 16;

        // 决定一个消费者如何等待生产者将Event置入Disruptor，其所有实现都是针对消费者线程的。
        // BlockingWaitStrategy: 最低效的策略，但其对CPU的消耗最小，并且在各种部署环境中能提供更加一致的性能表现，内部维护了一个重入锁ReentrantLock和Condition
        // SleepingWaitStrategy: 性能表现和BlockingWaitStrategy差不多，对CPU的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景；是一种无锁的方式
        // YieldingWaitStrategy: 性能最好，适合用于低延迟的系统；在要求极高性能且事件处理线程数小于CPU逻辑核心数的场景中，推荐使用此策略；
        // 例如，CPU开启超线程的特性；也是无锁的实现，只要是无锁的实现，signalAllWhenBlocking()都是空实现

        // SINGLE: 采用单消费者模式
        // MULTI: 多个消费者，每个消费者消费不同数据。也就是说每个消费者竞争数据，竞争到消费，其他消费者没有机会
        Disruptor<E> disruptor = new Disruptor<>(
                eventFactory, ringBufferSize, threadFactory,
                ProducerType.SINGLE, new YieldingWaitStrategy());

        // 设置事件业务处理器---消费者
        disruptor.handleEventsWith(eventHandler);

        // 启动disruptor线程
        disruptor.start();

        // 获取RingBuffer环，用于接收生产者生产的事件
        return disruptor.getRingBuffer();
    }
}
