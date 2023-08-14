package com.spring.boot.reactive.web.flux.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 问候实体
 *
 * @author guang.yi
 * @since 2023/8/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Greeting {
    /**
     * 消息
     */
    private String message;
}
