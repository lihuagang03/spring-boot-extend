package com.spring.boot.disruptor.example.event.handler;

import com.lmax.disruptor.EventHandler;
import com.spring.boot.disruptor.example.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

import static com.spring.boot.disruptor.example.util.GsonUtil.GSON;

/**
 * 订单业务事件处理器【消费者】
 *
 * @author lihuagang
 */
@Slf4j
public class OrderEventHandler implements EventHandler<OrderEvent> {
    private static final AtomicInteger index = new AtomicInteger(0);

    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        log.info("共收到消息数量: {}", index.incrementAndGet());
        if (event == null) {
            return;
        }
        log.info("需要处理的信息: {}", GSON.toJson(event));
    }
}
