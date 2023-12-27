package com.spring.boot.observability.example.service.impl;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.BeanUtilsHashMapper;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

import com.spring.boot.observability.example.domain.entity.UserEntity;
import com.spring.boot.observability.example.domain.repository.UserDomainRepository;
import com.spring.boot.observability.example.model.UserModel;
import com.spring.boot.observability.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
public class UserServiceImpl implements UserService {

    private final UserDomainRepository userDomainRepository;

    private final RedisTemplate<String, UserEntity> userRedisTemplate;
    private final RedisTemplate<Object, Object> redisTemplate;

    private final RocketMQTemplate rocketMQTemplate;

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
    }

    @Override
    public Mono<UserModel> getById(Long id) {
        return Optional.of(id)
                .map(this::getUserEntityById)
                .map(this::sendUserMessage)
                .map(Mono::just)
                .orElse(Mono.empty());
    }

    /**
     * formats: `topicName:tags`
     */
    private static final String TOPIC_NAME_TAGS = "userTopicName:userTags";

    private UserModel sendUserMessage(UserEntity userEntity) {
        SendResult sendResult = rocketMQTemplate.syncSend(TOPIC_NAME_TAGS, userEntity);
        log.info("sendResult={}", sendResult);
        if (sendResult.getSendStatus() != SendStatus.SEND_OK) {
            // 消息发送失败
            // 如何处理
            log.error("sendUserMessage fail, sendResult={}, userEntity={}", sendResult, userEntity);
        }
        return applyAsUserModel(userEntity);
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
        redisTemplate.<String, String>opsForHash()
                .putAll(cacheKey + ":BeanUtils", BEAN_UTILS_HASH_MAPPER.toHash(userEntity));
//        redisTemplate.<byte[], byte[]>opsForHash()
//                .putAll(cacheKey + ":Object-to-Hash", OBJECT_HASH_MAPPER.toHash(userEntity));
        return userEntity;
    }

    private static String applyAsCacheKey(Long id) {
        return "user:" + id;
    }

}
