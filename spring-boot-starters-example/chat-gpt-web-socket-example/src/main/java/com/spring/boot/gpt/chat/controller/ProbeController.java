package com.spring.boot.gpt.chat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 探针控制器
 * <pre>
 * 配置存活、就绪和启动探针
 * https://kubernetes.io/zh-cn/docs/tasks/configure-pod-container/configure-liveness-readiness-startup-probes/
 * </pre>
 *
 * @author lihuagang
 * @date 2023/6/14
 */
@RestController("probeController")
@RequestMapping("/probe")
public class ProbeController {

    /**
     * 健康检查
     */
    @GetMapping("/health-check")
    public String healthCheck() {
        return "ok";
    }

    /**
     * 容器配置存活探针
     */
    @GetMapping("/liveness")
    public String liveness() {
        return "liveness";
    }

    /**
     * 容器就绪探针
     */
    @GetMapping("/readiness")
    public String readiness() {
        return "readiness";
    }

    /**
     * 容器启动探针
     */
    @GetMapping("/startup")
    public String startup() {
        return "startup";
    }
}
