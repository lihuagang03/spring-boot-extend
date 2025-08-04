package com.spring.boot.disruptor.example.event;

import com.spring.boot.disruptor.example.value.UserId;
import lombok.*;

/**
 * 用户事件【实体】
 *
 * @author lihuagang
 */
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class UserEvent extends AbstractMessageEvent<String> {
    private UserId userId;
}
