package com.spring.boot.observability.example.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 交易单
 * <p>
 * 父子交易单模型
 *
 * @since 2023/12/16
 */
@Data
@Accessors(chain = true)
public class TradeModel {

    /**
     * 交易单身份
     */
    private Long id;

    /**
     * 父亲交易单身份
     */
    private Long parentId;

    /**
     * 用户身份
     */
    private Long userId;

    /**
     * 交易单状态
     */
    private Integer state;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
