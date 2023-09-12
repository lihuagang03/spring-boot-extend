package com.spring.boot.log.autoconfigure;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * 命令行运行器
 *
 * @author guang.yi
 * @since 2023/9/11
 * @see org.springframework.boot.CommandLineRunner
 */
@Slf4j
public class LogCommandLineRunner implements CommandLineRunner {

    public LogCommandLineRunner() {
        log.info("create LogCommandLineRunner");
    }

    @Override
    public void run(String... args) {
        log.info("run String..., args={}", Arrays.toString(args));
    }

}
