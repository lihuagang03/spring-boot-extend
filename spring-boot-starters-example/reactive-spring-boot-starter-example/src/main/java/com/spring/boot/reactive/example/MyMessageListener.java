package com.spring.boot.reactive.example;

import java.util.List;

/**
 * 消息侦听器
 *
 * @date 2023/7/27
 */
public interface MyMessageListener<T> {

    /**
     * 消息列表批量通知
     *
     * @param messages 消息列表
     */
    void onMessage(List<T> messages);

    /**
     * 处理完成
     */
    void processComplete();
}
