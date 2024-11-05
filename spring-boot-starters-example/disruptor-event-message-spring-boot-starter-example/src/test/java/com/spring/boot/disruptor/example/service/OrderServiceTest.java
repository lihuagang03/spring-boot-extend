package com.spring.boot.disruptor.example.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * Test of {@link OrderService}
 *
 * @author lihuagang
 */
@SpringBootTest
class OrderServiceTest {
    @Resource
    private OrderService orderService;

    @Test
    void createOrder() {
        for (int i = 0; i < 5; i++) {
            orderService.createOrder(Integer.toString(i), UUID.randomUUID().toString());
        }
    }
}