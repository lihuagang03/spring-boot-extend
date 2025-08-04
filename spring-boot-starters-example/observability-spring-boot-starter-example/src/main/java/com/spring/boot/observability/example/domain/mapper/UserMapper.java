package com.spring.boot.observability.example.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.observability.example.domain.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户实体映射器
 *
 * @since 2023/6/10
 * @see com.baomidou.mybatisplus.core.mapper.BaseMapper
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    //
}
