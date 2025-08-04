package com.spring.boot.autoconfigure.jdbc;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.spring.boot.autoconfigure.jdbc.metadata.DataSourcePoolMetadataProvidersConfiguration;
import lombok.extern.slf4j.Slf4j;

/**
 * Auto-configuration for Druid DataSource.
 *
 * @see org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
 * @see com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure
 */
@Slf4j
@AutoConfiguration
@ConditionalOnProperty(
        name = "spring.datasource.type",
        havingValue = "com.alibaba.druid.pool.DruidDataSource",
        matchIfMissing = true
)
@ConditionalOnClass(DruidDataSource.class)
@AutoConfigureAfter(DruidDataSourceAutoConfigure.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(DataSourceProperties.class)
@Import(DataSourcePoolMetadataProvidersConfiguration.class)
public class DruidDataSourceAutoConfiguration {
    public DruidDataSourceAutoConfiguration() {
        log.info("DruidDataSourceAutoConfiguration create");
    }
}
