package com.alibaba.csp.sentinel.demo.datasource.apollo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.demo.datasource.apollo.service.SentinelService;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * 流量治理组件的控制器
 *
 * @since 2024/9/5
 */
@RestController
public class SentinelController {
    private final SentinelService sentinelService;

    public SentinelController(
            SentinelService sentinelService
    ) {
        this.sentinelService = sentinelService;
    }

//    @SentinelResource(value = "/hello", blockHandler = "helloBlockHandler")
    @GetMapping(value = "/hello/{name}")
    public String sayHello(@PathVariable String name) {
        return sentinelService.sayHello(name);
    }

    public String helloBlockHandler(String name, BlockException e) {
        return name + ": 请稍后重试";
    }
}
