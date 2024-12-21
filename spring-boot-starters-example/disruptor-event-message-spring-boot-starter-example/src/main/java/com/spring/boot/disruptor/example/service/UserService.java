package com.spring.boot.disruptor.example.service;

import com.lmax.disruptor.RingBuffer;
import com.spring.boot.disruptor.example.event.UserEvent;
import com.spring.boot.disruptor.example.value.UserId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.spring.boot.disruptor.example.util.GsonUtil.GSON;

/**
 * 用户域服务
 *
 * @author lihuagang
 * @since 2024/11/5
 */
@Slf4j
@Service
public class UserService {
    private final RingBuffer<UserEvent> userEventRingBuffer;

    public UserService(
            RingBuffer<UserEvent> userEventRingBuffer
    ) {
        this.userEventRingBuffer = userEventRingBuffer;
        log.info("create UserService");
    }

    public void createUser(String type, String message) {
        log.info("send message={}", message);
        // 获取下一个Event槽的下标
        long sequence = userEventRingBuffer.next();
        try {
            // 给Event填充数据
            UserEvent userEvent = userEventRingBuffer.get(sequence);
            userEvent.setUserId(new UserId(sequence));
            userEvent.setType(type);
            userEvent.setMessage(message);
            log.info("fill message={}", GSON.toJson(userEvent));
        } catch (Exception e) {
            log.error("failed to add event to RingBuffer, message={}", message, e);
        } finally {
            // 发布Event，激活观察者去消费，将sequence传递给消费者
            // 注意，最后的publish方法必须放在finally中以确保必须得到调用；如果某个请求的sequence未被提交将会堵塞后续的发布操作或者其他的生产者
            userEventRingBuffer.publish(sequence);
        }
    }
}
