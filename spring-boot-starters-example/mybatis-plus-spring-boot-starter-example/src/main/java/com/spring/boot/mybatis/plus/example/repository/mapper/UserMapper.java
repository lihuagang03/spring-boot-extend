package com.spring.boot.mybatis.plus.example.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.mybatis.plus.example.repository.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户实体映射器
 *
 * @author lihuagang
 * @date 2023/6/10
 * @see com.baomidou.mybatisplus.core.mapper.BaseMapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    //
}
