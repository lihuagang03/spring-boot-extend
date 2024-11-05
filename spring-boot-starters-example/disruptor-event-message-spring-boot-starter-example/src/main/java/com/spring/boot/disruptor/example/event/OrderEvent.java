package com.spring.boot.disruptor.example.event;

import com.spring.boot.disruptor.example.value.OrderId;
import lombok.*;

/**
 * 订单事件【实体】
 *
 * @author lihuagang
 */
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class OrderEvent extends AbstractMessageEvent<String> {
    private OrderId orderId;
}
