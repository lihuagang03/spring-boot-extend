package com.spring.boot.observability.example.domain.repository;

import com.spring.boot.observability.example.domain.entity.UserEntity;

/**
 * 用户领域仓储
 *
 * @since 2023/12/16
 */
public interface UserDomainRepository {

    /**
     * 保存用户
     *
     * @param entity 实体
     * @return 操作结果
     */
    boolean save(UserEntity entity);

    /**
     * 通过身份获取用户
     *
     * @param id 身份
     * @return 实体
     */
    UserEntity getById(Long id);

}
