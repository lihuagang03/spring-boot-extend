package com.alibaba.csp.sentinel.demo.datasource.apollo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

/**
 * 应用程序启动入口
 *
 * @since 2024/9/5
 */
@EnableApolloConfig
@SpringBootApplication
public class SentinelApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication.class, args);
    }
}
