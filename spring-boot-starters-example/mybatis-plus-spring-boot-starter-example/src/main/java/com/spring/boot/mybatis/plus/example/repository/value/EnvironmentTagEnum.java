package com.spring.boot.mybatis.plus.example.repository.value;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 环境标签
 *
 * @since 2024/5/17
 */
@Getter
@AllArgsConstructor
public enum EnvironmentTagEnum {
    /**
     * 开发环境
     */
    DEVELOP_ENV(0),
    /**
     * 测试环境
     */
    TEST_ENV(1),
    /**
     * 预发环境
     */
    STAGING_ENV(2),
    /**
     * 生产环境
     */
    PRODUCTION_ENV(3),
    ;

    /**
     * 标记数据库存的值是code
     */
    @EnumValue
    private final int code;
}
