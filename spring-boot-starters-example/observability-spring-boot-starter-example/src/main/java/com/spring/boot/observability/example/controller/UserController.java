package com.spring.boot.observability.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

import com.spring.boot.observability.example.model.UserModel;
import com.spring.boot.observability.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
//import org.apache.skywalking.apm.toolkit.trace.TraceContext;

/**
 * 用户控制器
 *
 * @since 2023/12/16
 */
@Slf4j
@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public Mono<UserModel> getById(@PathVariable("userId") Long userId) {
//        // CorrelationContext-关联上下文，sw8-correlation
////        TraceContext.putCorrelation("trace_id", TraceContext.traceId());
//        TraceContext.putCorrelation("sw8_userId", "123456789");
//        TraceContext.putCorrelation("scene", "p0");
////        TraceContext.putCorrelation("scene.label", "p0");
//
//        TraceContext.putCorrelation("scene-tag", "p0");
//        TraceContext.putCorrelation("cyborg-flow", "true");

        Mono<UserModel> mono = userService.getById(userId);
        // mono=MonoJust
//        log.info("mono={}", mono);
        // mono={"scanAvailable":true}
//        log.info("mono={}", JSON.toJSONString(mono));
        return mono;
    }
}
