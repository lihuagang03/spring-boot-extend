package com.spring.boot.reactive.web.flux.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 问候【实体】
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
