package com.spring.boot.disruptor.example.event.factory;

import com.lmax.disruptor.EventFactory;
import com.spring.boot.disruptor.example.event.OrderEvent;

/**
 * 订单事件工厂
 *
 * @author lihuagang
 */
public class OrderEventFactory implements EventFactory<OrderEvent> {
    @Override
    public OrderEvent newInstance() {
        return new OrderEvent();
    }
}
