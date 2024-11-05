package com.spring.boot.disruptor.example.service;

import com.lmax.disruptor.RingBuffer;
import com.spring.boot.disruptor.example.event.OrderEvent;
import com.spring.boot.disruptor.example.value.OrderId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.spring.boot.disruptor.example.util.GsonUtil.GSON;

/**
 * 订单域服务
 *
 * @author lihuagang
 */
@Slf4j
@Service
public class OrderService {
    private final RingBuffer<OrderEvent> orderEventRingBuffer;

    public OrderService(
            RingBuffer<OrderEvent> orderEventRingBuffer
    ) {
        this.orderEventRingBuffer = orderEventRingBuffer;
        log.info("create OrderService");
    }

    public void createOrder(String type, String message) {
        log.info("send message={}", message);
        // 获取下一个Event槽的下标
        long sequence = orderEventRingBuffer.next();
        try {
            // 给Event填充数据
            OrderEvent orderEvent = orderEventRingBuffer.get(sequence);
            orderEvent.setOrderId(new OrderId(sequence));
            orderEvent.setType(type);
            orderEvent.setMessage(message);
            log.info("fill message={}", GSON.toJson(orderEvent));
        } catch (Exception e) {
            log.error("failed to add event to RingBuffer, message={}", message, e);
        } finally {
            // 发布Event，激活观察者去消费，将sequence传递给消费者
            // 注意，最后的publish方法必须放在finally中以确保必须得到调用；如果某个请求的sequence未被提交将会堵塞后续的发布操作或者其他的生产者
            orderEventRingBuffer.publish(sequence);
        }
    }
}
