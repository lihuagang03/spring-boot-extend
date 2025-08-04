package com.spring.boot.disruptor.example.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * Test of {@link UserService}
 *
 * @author lihuagang
 */
@SpringBootTest
class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    void createUser() {
        for (int i = 0; i < 5; i++) {
            userService.createUser(Integer.toString(i), UUID.randomUUID().toString());
        }
    }
}