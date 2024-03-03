package com.spring.boot.observability.example.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.BeanUtilsHashMapper;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.spring.boot.observability.example.domain.entity.UserEntity;
import com.spring.boot.observability.example.domain.repository.UserDomainRepository;
import com.spring.boot.observability.example.model.UserModel;
import com.spring.boot.observability.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

/**
 * 用户服务实现
 *
 * @author guang.yi
 * @since 2023/12/16
 */
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService, InitializingBean {

    private final UserDomainRepository userDomainRepository;

    private final RedisTemplate<String, UserEntity> userRedisTemplate;
    private final RedisTemplate<Object, Object> redisTemplate;

    private final RocketMQTemplate rocketMQTemplate;

    private final ThreadPoolExecutor threadPoolExecutor;

    public UserServiceImpl(
            UserDomainRepository userDomainRepository,
            RedisTemplate<String, UserEntity> userRedisTemplate,
            RedisTemplate<Object, Object> redisTemplate,
            RocketMQTemplate rocketMQTemplate
    ) {
        this.userDomainRepository = userDomainRepository;
        this.userRedisTemplate = userRedisTemplate;
        this.redisTemplate = redisTemplate;
        this.rocketMQTemplate = rocketMQTemplate;

        this.threadPoolExecutor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1),
                new ThreadFactoryBuilder()
                        .setNameFormat("observability-thread-%d")
                        .setDaemon(true)
                        .build()
        );
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        rocketMQTemplate.getConsumer()
                .subscribe(MESSAGE_TOPIC_NAME, MESSAGE_TAGS);

        threadPoolExecutor.execute(() -> {
                    List<UserEntity> userEntityList = rocketMQTemplate.receive(UserEntity.class);
                    log.info("receive userEntityList={}", userEntityList);
                });
    }

    @Override
    public Mono<UserModel> getById(Long id) {
        return Optional.of(id)
                .map(this::getUserEntityById)
                .map(this::sendUserMessage)
                .map(Mono::just)
                .orElse(Mono.empty());
    }

    public static final String MESSAGE_TOPIC_NAME = "userTopicName";
    public static final String MESSAGE_TAGS = "userTags";
    /**
     * formats: `topicName:tags`
     */
    private static final String MESSAGE_TOPIC_NAME_TAGS = MESSAGE_TOPIC_NAME + ':' + MESSAGE_TAGS;

    private UserModel sendUserMessage(UserEntity userEntity) {
        // 普通消息
        SendResult sendResult = rocketMQTemplate.syncSend(MESSAGE_TOPIC_NAME_TAGS, userEntity, 10_000L);
        log.info("syncSend sendResult={}", sendResult);
        if (sendResult.getSendStatus() != SendStatus.SEND_OK) {
            // 消息发送失败
            // 如何处理
            log.error("sendUserMessage fail, sendResult={}, userEntity={}", sendResult, userEntity);
        }
        // 顺序消息
        // ...
        // 定时/延时消息
        sendResult = rocketMQTemplate.syncSendDelayTimeSeconds(MESSAGE_TOPIC_NAME_TAGS, userEntity, 30L);
        log.info("syncSendDelayTimeSeconds sendResult={}", sendResult);
        // 事务消息
        // ...
        // 异步消息
        rocketMQTemplate.asyncSend(MESSAGE_TOPIC_NAME_TAGS, userEntity,
                new CustomSendCallback(), 10_000L);

        return applyAsUserModel(userEntity);
    }

    @Slf4j
    private static class CustomSendCallback implements SendCallback {
        @Override
        public void onSuccess(SendResult sendResult) {
            log.info("message SendCallback.onSuccess(), sendResult={}", sendResult);
        }

        @Override
        public void onException(Throwable e) {
            log.info("message SendCallback.onException()", e);
        }
    }

    private static UserModel applyAsUserModel(UserEntity userEntity) {
        return new UserModel()
                .setId(userEntity.getId())
                .setRealName(userEntity.getRealName())
                .setMobile(userEntity.getMobile())
                .setCreateTime(userEntity.getCreateTime())
                .setUpdateTime(userEntity.getUpdateTime());
    }

    /**
     * HashMapper
     */
    private static final Jackson2HashMapper JACKSON_2_HASH_MAPPER = new Jackson2HashMapper(true);
    private static final BeanUtilsHashMapper<UserEntity> BEAN_UTILS_HASH_MAPPER = new BeanUtilsHashMapper<>(UserEntity.class);
//    private static final ObjectHashMapper OBJECT_HASH_MAPPER = new ObjectHashMapper();

    /**
     * <a href="https://docs.spring.io/spring-data/redis/reference/redis/hash-mappers.html">
     *     Spring Data Redis / Redis / Hash Mapping</a>
     */
    private UserEntity getUserEntityById(Long id) {
        // get from Cache
        String cacheKey = applyAsCacheKey(id);
        Map<String, Object> entries = redisTemplate.<String, Object>opsForHash()
                .entries(cacheKey);
        if (!entries.isEmpty()) {
            return JACKSON_2_HASH_MAPPER.fromHash(UserEntity.class, entries);
        }
//        // 所有字段值都为null
//        Map<String, String> entries = redisTemplate.<String, String>opsForHash()
//                .entries(cacheKey + ":BeanUtils");
//        if (!entries.isEmpty()) {
//            return BEAN_UTILS_HASH_MAPPER.fromHash(UserEntity.class, entries);
//        }

        // get from Database
        UserEntity userEntity = userDomainRepository.getById(id);
        if (userEntity == null) {
            return null;
        }

        // set to Cache
        redisTemplate.<String, Object>opsForHash()
                .putAll(cacheKey, JACKSON_2_HASH_MAPPER.toHash(userEntity));
        threadPoolExecutor.execute(() -> {
                    String key = cacheKey + ":BeanUtils";
                    log.info("async redisTemplate opsForHash, key={}", key);
                    redisTemplate.<String, String>opsForHash()
                            .putAll(key, BEAN_UTILS_HASH_MAPPER.toHash(userEntity));
                }
        );
//        redisTemplate.<String, String>opsForHash()
//                .putAll(cacheKey + ":BeanUtils", BEAN_UTILS_HASH_MAPPER.toHash(userEntity));
//        redisTemplate.<byte[], byte[]>opsForHash()
//                .putAll(cacheKey + ":Object-to-Hash", OBJECT_HASH_MAPPER.toHash(userEntity));
        return userEntity;
    }

    private static String applyAsCacheKey(Long id) {
        return "user:" + id;
    }

}
