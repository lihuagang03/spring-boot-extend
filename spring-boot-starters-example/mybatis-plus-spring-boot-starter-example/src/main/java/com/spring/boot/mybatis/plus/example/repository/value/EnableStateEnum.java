package com.spring.boot.mybatis.plus.example.repository.value;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 启用状态
 *
 * @since 2024/5/17
 */
@Getter
@AllArgsConstructor
public enum EnableStateEnum {
    /**
     * 未启用
     */
    NOT_ENABLE(0),
    /**
     * 启用
     */
    ENABLE(1),
    ;

    /**
     * 标记数据库存的值是code
     */
    @EnumValue
    private final int code;
}
