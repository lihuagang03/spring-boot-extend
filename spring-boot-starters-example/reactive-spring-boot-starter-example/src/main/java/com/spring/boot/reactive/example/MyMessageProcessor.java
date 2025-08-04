package com.spring.boot.reactive.example;

import java.util.List;

/**
 * 消息处理器
 *
 * @date 2023/7/27
 */
public interface MyMessageProcessor<T> {

    /**
     * 注册消息侦听器
     *
     * @param messageListener 消息侦听器
     */
    void register(MyMessageListener<T> messageListener);

    /**
     * 消息列表批量处理
     *
     * @param messages 消息列表
     */
    void message(List<T> messages);

    /**
     * 处理完成
     */
    void processComplete();

    /**
     * 获取历史消息列表
     *
     * @param n 消息列表批次号
     * @return 历史消息列表
     */
    List<T> getHistory(long n);
}
