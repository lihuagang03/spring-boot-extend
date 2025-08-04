package com.spring.boot.observability.example.service;

import com.spring.boot.observability.example.model.TradeModel;

/**
 * 交易单服务
 *
 * @since 2023/12/16
 */
public interface TradeService {
    /**
     * 通过身份获取用户
     *
     * @param id 身份
     * @return 实体
     */
    TradeModel getById(Long id);
}
