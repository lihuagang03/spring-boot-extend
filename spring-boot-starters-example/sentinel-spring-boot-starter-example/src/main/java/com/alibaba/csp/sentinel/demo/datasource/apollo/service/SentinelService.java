package com.alibaba.csp.sentinel.demo.datasource.apollo.service;

import org.springframework.stereotype.Service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * 流量治理组件的服务
 *
 * @since 2024/9/5
 */
@Service
public class SentinelService {
    @SentinelResource(value = "sayHello", blockHandler = "sayHelloBlockHandler")
    public String sayHello(String name) {
        return "Hello, " + name;
    }

    public String sayHelloBlockHandler(String str , BlockException e) {
        return str + ": 请稍后重试";
    }
}
