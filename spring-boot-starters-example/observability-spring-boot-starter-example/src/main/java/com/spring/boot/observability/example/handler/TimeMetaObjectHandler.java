package com.spring.boot.observability.example.handler;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 元对象字段填充控制器，实现公共字段自动写入。
 * <p></p>
 * <a href="https://baomidou.com/guides/auto-fill-field/">自动填充字段</a>
 *
 * @since 2023/6/10
 * @see MetaObjectHandler
 */
@Slf4j
@Component
public class TimeMetaObjectHandler implements MetaObjectHandler {
    public TimeMetaObjectHandler() {
        log.info("create TimeMetaObjectHandler");
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.strictInsertFill(metaObject,
                "createTime", LocalDateTime.class, now);
        this.strictInsertFill(metaObject,
                "updateTime", LocalDateTime.class, now);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.strictUpdateFill(metaObject,
                "updateTime", LocalDateTime.class, now);
    }
}
