package com.spring.boot.disruptor.example.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 消息事件【实体】
 *
 * @author lihuagang
 */
@Setter
@Getter
@ToString
public abstract class AbstractMessageEvent<T> {
    private String type;
    private T message;
}
