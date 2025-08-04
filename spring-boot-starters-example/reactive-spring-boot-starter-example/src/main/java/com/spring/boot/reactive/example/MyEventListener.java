package com.spring.boot.reactive.example;

import java.util.List;

/**
 * 事件侦听器
 * <p>
 * https://github.com/reactor/reactor-core/blob/main/reactor-core/src/test/java/reactor/guide/GuideTests.java#L863
 * <p>
 * Imagine that you use a listener-based API. It processes data by chunks and has two events:
 * (1) a chunk of data is ready and (2) the processing is complete (terminal event),
 * as represented in the MyEventListener interface:
 *
 * @since 2023/7/27
 */
public interface MyEventListener<T> {

    /**
     * 数据块通知
     *
     * @param chunk 数据块
     */
    void onDataChunk(List<T> chunk);

    /**
     * 处理完成
     */
    void processComplete();
}
