package com.spring.boot.gpt.chat.web.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocket配置，注册处理器
 * <pre>
 * <a href="https://docs.spring.io/spring-framework/docs/5.3.x/reference/html/web.html#websocket">WebSockets</a>
 *
 * WebSocket API
 *
 * ws://localhost:8080/gpt/chat/web-socket/123456789_16:333666999
 * Host: localhost:8080
 * Upgrade: websocket
 * Connection: Upgrade
 * Sec-WebSocket-Key: KW2SMxi93DAvbzP9caUEVw==
 * Sec-WebSocket-Version: 13
 * </pre>
 *
 * @since 2023/6/14
 * @see WebSocketConfigurer
 */
@Slf4j
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    public WebSocketConfig() {
        log.info("create WebSocketConfig");
    }

    @Bean
    public WebSocketHandler serverTextWebSocketHandler() {
        return new ServerTextWebSocketHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(serverTextWebSocketHandler(), "/gpt/chat/web-socket/{session-id}");
    }
}
