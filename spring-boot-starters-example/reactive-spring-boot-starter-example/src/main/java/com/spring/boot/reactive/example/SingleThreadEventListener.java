package com.spring.boot.reactive.example;

import java.util.List;

/**
 * 单线程的事件侦听器
 *
 * @since 2023/7/27
 */
public interface SingleThreadEventListener<T> extends MyEventListener<T> {

    /**
     * 数据块通知
     *
     * @param chunk 数据块
     */
    @Override
    void onDataChunk(List<T> chunk);

    /**
     * 处理完成
     */
    @Override
    void processComplete();

    /**
     * 处理错误
     *
     * @param e 异常
     */
    void processError(Throwable e);
}
