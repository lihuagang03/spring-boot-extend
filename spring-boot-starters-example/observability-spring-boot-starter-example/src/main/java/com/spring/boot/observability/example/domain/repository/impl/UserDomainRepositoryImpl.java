package com.spring.boot.observability.example.domain.repository.impl;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Repository;

import com.spring.boot.observability.example.domain.entity.UserEntity;
import com.spring.boot.observability.example.domain.repository.UserDomainRepository;

/**
 * 用户领域仓储实现
 *
 * @since 2023/12/16
 */
@Repository("userDomainRepository")
public class UserDomainRepositoryImpl implements UserDomainRepository {

    @Override
    public boolean save(UserEntity entity) {
        if (entity.getCreateTime() == null || entity.getUpdateTime() == null) {
            LocalDateTime now = LocalDateTime.now();
            entity.setCreateTime(now)
                    .setUpdateTime(now);
        }
        return true;
    }

    @Override
    public UserEntity getById(Long id) {
        int random = ThreadLocalRandom.current().nextInt();
        String realName = ((random & 1) == 0) ? "张三" : "李四";
        LocalDateTime now = LocalDateTime.now();

        return new UserEntity()
                .setId(id)
                .setRealName(realName)
                .setMobile("13555666888")
                .setCreateTime(now)
                .setUpdateTime(now);
    }

}
