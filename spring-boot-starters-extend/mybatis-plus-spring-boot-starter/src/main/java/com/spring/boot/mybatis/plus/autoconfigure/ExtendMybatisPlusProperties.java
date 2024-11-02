package com.spring.boot.mybatis.plus.autoconfigure;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * MybatisPlus 配置属性集
 *
 * @since 2023/6/9
 * @see com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties
 */
@Slf4j
@Data
@Accessors(chain = true)
@ConfigurationProperties(Constants.MYBATIS_PLUS)
public class ExtendMybatisPlusProperties {
    /**
     * MybatisPlus 支持的数据库类型，主要用于分页方言
     */
    private DbType dbType;

    public ExtendMybatisPlusProperties() {
        log.info("create ExtendMybatisPlusProperties");
    }
}
