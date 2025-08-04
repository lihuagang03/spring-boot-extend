package com.spring.boot.reactive.web.flux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 应用程序启动入口
 *
 * @author lihuagang
 * @since 2023/8/13
 */
@SpringBootApplication
public class SpringWebFluxApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringWebFluxApplication.class, args);

        GreetingClient greetingClient = context.getBean(GreetingClient.class);
        // We need to block for the content here or the JVM might exit before the message is logged
        greetingClient.getMessage()
                .doOnSuccess(message -> System.out.println(">> message = " + message))
                .subscribe();
        // >> message = Hello, Spring!
    }
}
