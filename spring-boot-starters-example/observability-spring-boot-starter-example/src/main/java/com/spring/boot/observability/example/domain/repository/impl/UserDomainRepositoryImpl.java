package com.spring.boot.observability.example.domain.repository.impl;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.boot.observability.example.domain.entity.UserEntity;
import com.spring.boot.observability.example.domain.mapper.UserMapper;
import com.spring.boot.observability.example.domain.repository.UserDomainRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户领域仓储实现
 *
 * @since 2023/12/16
 */
@Slf4j
@Repository
public class UserDomainRepositoryImpl extends ServiceImpl<UserMapper, UserEntity>
        implements UserDomainRepository {
    public UserDomainRepositoryImpl() {
        log.info("UserDomainRepositoryImpl init");
    }
}
