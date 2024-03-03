package com.spring.boot.observability.example.message;

import org.springframework.stereotype.Component;

import com.spring.boot.observability.example.domain.entity.UserEntity;
import com.spring.boot.observability.example.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

/**
 * RocketMQ消息消费者
 *
 * @author guang.yi
 * @since 2024/3/3
 */
@Slf4j
@Component
@RocketMQMessageListener(
        topic = UserServiceImpl.MESSAGE_TOPIC_NAME,
        consumerGroup = "user-consumer-group-" + UserServiceImpl.MESSAGE_TOPIC_NAME,
        selectorExpression = UserServiceImpl.MESSAGE_TAGS,
        enableMsgTrace = true
)
public class RocketMqMessageListenerConsumer implements RocketMQListener<UserEntity> {

    public RocketMqMessageListenerConsumer() {
        log.info("create RocketMqMessageListenerConsumer");
    }

    @Override
    public void onMessage(UserEntity message) {
        log.info("receive message, {}", message);
    }

}
