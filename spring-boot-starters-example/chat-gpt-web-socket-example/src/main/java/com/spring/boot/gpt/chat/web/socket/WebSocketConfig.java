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
 * WebSocket API
 * https://docs.spring.io/spring-framework/docs/5.3.x/reference/html/web.html#websocket-server
 * </pre>
 *
 * @author lihuagang
 * @date 2023/6/14
 * @see org.springframework.web.socket.config.annotation.WebSocketConfigurer
 */
@Slf4j
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    public WebSocketConfig() {
        log.info("create WebSocketConfig instance");
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
