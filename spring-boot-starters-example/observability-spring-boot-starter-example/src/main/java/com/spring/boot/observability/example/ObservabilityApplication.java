package com.spring.boot.observability.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用程序启动入口
 *
 * @since 2023/9/18
 */
@SpringBootApplication
public class ObservabilityApplication {
    public static void main(String[] args) {
        SpringApplication.run(ObservabilityApplication.class, args);
    }
}
