package com.spring.boot.observability.example.domain.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.boot.observability.example.domain.entity.UserEntity;

/**
 * 用户领域仓储
 *
 * @since 2023/12/16
 */
public interface UserDomainRepository extends IService<UserEntity> {
    //
}
