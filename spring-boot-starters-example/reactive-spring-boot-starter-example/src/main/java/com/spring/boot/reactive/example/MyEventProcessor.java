package com.spring.boot.reactive.example;

import java.util.List;

/**
 * 事件处理器
 * <p>
 * https://github.com/reactor/reactor-core/blob/main/reactor-core/src/test/java/reactor/guide/GuideTests.java#L868
 *
 * @author guangyi
 * @since 2023/7/27
 */
public interface MyEventProcessor {

    /**
     * 注册事件侦听器
     *
     * @param eventListener 事件侦听器
     */
    void register(MyEventListener<String> eventListener);

    /**
     * 处理数据块
     *
     * @param values 数据块
     */
    void dataChunk(List<String> values);

    /**
     * 处理完成
     */
    void processComplete();

    /**
     * 处理错误
     *
     * @param e 异常
     */
    void processError(Throwable e);
}
