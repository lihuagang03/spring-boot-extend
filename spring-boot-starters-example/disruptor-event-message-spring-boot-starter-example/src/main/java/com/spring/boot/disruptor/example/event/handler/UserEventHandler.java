package com.spring.boot.disruptor.example.event.handler;

import com.lmax.disruptor.EventHandler;
import com.spring.boot.disruptor.example.event.UserEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

import static com.spring.boot.disruptor.example.util.GsonUtil.GSON;

/**
 * 用户业务事件处理器【消费者】
 *
 * @author lihuagang
 */
@Slf4j
public class UserEventHandler implements EventHandler<UserEvent> {
    private static final AtomicInteger index = new AtomicInteger(0);

    @Override
    public void onEvent(UserEvent event, long sequence, boolean endOfBatch) throws Exception {
        log.info("共收到消息数量: {}", index.incrementAndGet());
        if (event == null) {
            return;
        }
        log.info("需要处理的信息: {}", GSON.toJson(event));
    }
}
