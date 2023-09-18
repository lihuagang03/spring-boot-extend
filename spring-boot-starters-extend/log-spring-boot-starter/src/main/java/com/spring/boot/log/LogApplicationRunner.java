package com.spring.boot.log;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * 应用运行程序
 *
 * @author guang.yi
 * @since 2023/9/11
 * @see org.springframework.boot.ApplicationRunner
 */
@Slf4j
public class LogApplicationRunner implements ApplicationRunner {

    public LogApplicationRunner() {
        log.info("create LogApplicationRunner");
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("run ApplicationArguments, args={}", args);
    }

}
