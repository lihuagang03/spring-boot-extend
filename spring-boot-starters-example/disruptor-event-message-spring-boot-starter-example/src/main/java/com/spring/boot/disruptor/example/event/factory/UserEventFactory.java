package com.spring.boot.disruptor.example.event.factory;

import com.lmax.disruptor.EventFactory;
import com.spring.boot.disruptor.example.event.UserEvent;

/**
 * 用户事件工厂
 *
 * @author lihuagang
 */
public class UserEventFactory implements EventFactory<UserEvent> {
    @Override
    public UserEvent newInstance() {
        return new UserEvent();
    }
}
