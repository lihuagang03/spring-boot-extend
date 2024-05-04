package com.spring.boot.reactive.web.flux.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create a Domain Class
 * 咖啡实体
 *
 * @since 2023/8/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
    private String id;
    private String name;
}
