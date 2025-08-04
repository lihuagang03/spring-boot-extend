package com.spring.boot.disruptor.example.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 对象事件【实体】
 *
 * @author lihuagang
 */
@Setter
@Getter
@ToString
public class ObjectEvent<T> {
    private String type;
    private T message;
}
