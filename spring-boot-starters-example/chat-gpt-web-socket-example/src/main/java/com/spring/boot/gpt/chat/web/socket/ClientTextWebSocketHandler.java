package com.spring.boot.gpt.chat.web.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 扩展的文本处理器
 *
 * @author lihuagang
 * @since 2023/5/11
 * @see org.springframework.web.socket.WebSocketHandler
 * @see org.springframework.web.socket.handler.AbstractWebSocketHandler
 * @see org.springframework.web.socket.handler.TextWebSocketHandler
 */
@Slf4j
public class ClientTextWebSocketHandler extends TextWebSocketHandler {

    private final transient Object lock = new Object();

    private String outputText;

    public String getOutputText() {
        synchronized (lock) {
            return outputText;
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Client connection established, session={}", session);
        super.afterConnectionEstablished(session);
    }

//    @Override
//    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        super.handleMessage(session, message);
//    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        String payload = message.getPayload();
        log.info("Client handleTextMessage, payload={}", payload);
        // 接收文本消息
        synchronized (lock) {
            outputText = payload;
        }
    }

//    @Override
//    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
//        super.handlePongMessage(session, message);
//    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("Client handle transport error, session={}", session, exception);
        super.handleTransportError(session, exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("Client connection closed, session={}, status={}", session, status);
        if (!CloseStatus.NORMAL.equals(status)) {
            log.error("Client connection closed fail, session={}, status={}", session, status);
        }
        super.afterConnectionClosed(session, status);
    }
}
