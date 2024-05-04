package com.spring.boot.observability.example.domain.repository.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

import com.spring.boot.observability.example.domain.entity.TradeEntity;
import com.spring.boot.observability.example.domain.repository.TradeDomainRepository;

/**
 * 交易单领域仓储实现
 *
 * @since 2023/12/16
 */
@Repository("tradeDomainRepository")
public class TradeDomainRepositoryImpl implements TradeDomainRepository {

    @Override
    public boolean save(TradeEntity entity) {
        if (entity.getCreateTime() == null || entity.getUpdateTime() == null) {
            LocalDateTime now = LocalDateTime.now();
            entity.setCreateTime(now)
                    .setUpdateTime(now);
        }
        return true;
    }

    @Override
    public TradeEntity getById(Long id) {
        LocalDateTime now = LocalDateTime.now();

        return new TradeEntity()
                .setId(id)
//                .setParentId(0L)
                .setUserId(123456L)
                .setState(0)
                .setCreateTime(now)
                .setUpdateTime(now);
    }

}
