package com.spring.boot.observability.example.message;

import org.springframework.stereotype.Component;

import com.spring.boot.observability.example.domain.entity.UserEntity;
import com.spring.boot.observability.example.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;

/**
 * RocketMQ消息消费者
 *
 * @since 2024/3/3
 */
@Slf4j
@Component
@RocketMQMessageListener(
        consumerGroup = "userConsumerGroup",
        topic = UserServiceImpl.MESSAGE_TOPIC_NAME,
//        selectorType = SelectorType.TAG,
        selectorExpression = UserServiceImpl.MESSAGE_TAGS, // "*"
//        consumeMode = ConsumeMode.CONCURRENTLY,
//        messageModel = MessageModel.CLUSTERING,
//        consumeThreadNumber = 20,
//        maxReconsumeTimes = -1,
        consumeTimeout = 1L, // 15L
//        replyTimeout = 3000,
        enableMsgTrace = true
//        customizedTraceTopic = RocketMQMessageListener.TRACE_TOPIC_PLACEHOLDER,
//        namespace = "",
//        delayLevelWhenNextConsume = 0,
//        suspendCurrentQueueTimeMillis = 1000,
//        awaitTerminationMillisWhenShutdown = 1000,
//        instanceName = "DEFAULT"
)
public class UserMessagePushConsumerRocketMqListener implements RocketMQListener<UserEntity> {

    public UserMessagePushConsumerRocketMqListener() {
        log.info("create UserMessagePushConsumerRocketMqListener");
    }

    @Override
    public void onMessage(UserEntity message) {
        log.info("receive message={}", message);
    }

}
