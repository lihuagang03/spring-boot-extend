package com.spring.boot.gpt.chat.web.socket;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link WebSocketClient}.
 *
 * @since 2023/6/15
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebSocketClientTest {
    /**
     * 套接字客户端
     */
    private final WebSocketClient webSocketClient;

    private static final String THREAD_NAME_PREFIX = "gpt.chat-";

    public WebSocketClientTest() {
        this.webSocketClient = WebSocketUtil.applyWebSocketClient(THREAD_NAME_PREFIX);
    }

    private static String applyChatId(Long orgId, Long userId) {
        return orgId + ":" + userId;
    }

    private static String applySessionId(String appId, String chatId) {
        return appId + '_' + chatId;
    }

    private static final String URI_TEMPLATE = "ws://localhost:8080/gpt/chat/web-socket/{session-id}";

    @Test
    void doCompletions() {
        String requestId = UUID.randomUUID().toString();
        // 阶段任务耗时统计
        StopWatch stopWatch = new StopWatch(requestId);
        stopWatch.start("doHandshake");

        Long orgId = 16L;
        Long userId = 333666999L;
        String appId = "123456789";

        ClientTextWebSocketHandler webSocketHandler = new ClientTextWebSocketHandler();
        String chatId = applyChatId(orgId, userId);
        String sessionId = applySessionId(appId, chatId);
        CompletableFuture<WebSocketSession> listenableFuture = webSocketClient
                .execute(webSocketHandler, URI_TEMPLATE, sessionId);
        stopWatch.stop();
        stopWatch.start("getWebSocketSession");

        long connectionTimeout = 3L;
        try (WebSocketSession webSocketSession = listenableFuture.get(connectionTimeout, TimeUnit.SECONDS)) {
            stopWatch.stop();
            stopWatch.start("sendMessage");

            String inputText = "OpenAI";
            webSocketSession.sendMessage(new TextMessage(inputText));

            // 模拟等待响应输出
            TimeUnit.MILLISECONDS.sleep(3L);
            log.info("sendMessage, inputText={}", inputText);

            String outputText = webSocketHandler.getOutputText();
            assertThat(outputText).isNotEmpty();
            assertThat(outputText).isEqualTo("ChatGPT");

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.error("get WebSocketSession fail", e);
        } catch (IOException e) {
            log.error("sendMessage fail", e);
        } finally {
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            log.info("doCompletions execute time, {}", stopWatch);
        }
    }
}
