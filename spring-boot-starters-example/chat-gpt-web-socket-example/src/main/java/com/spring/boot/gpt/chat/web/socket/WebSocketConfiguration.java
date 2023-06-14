package com.spring.boot.gpt.chat.web.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocket配置，注册处理器
 *
 * @author lihuagang
 * @date 2023/6/14
 * @see org.springframework.web.socket.config.annotation.WebSocketConfigurer
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    public WebSocketConfiguration() {
        log.info("create WebSocketConfiguration instance");
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ExtendTextWebSocketHandler(), "/gpt/chat/web-socket");
    }
}
