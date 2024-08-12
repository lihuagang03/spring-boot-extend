package com.spring.boot.autoconfigure.jdbc.metadata;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.jdbc.DataSourceUnwrapper;
import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidAbstractDataSourceMBean;
import com.alibaba.druid.pool.DruidDataSource;
import com.spring.boot.jdbc.metadata.DruidDataSourcePoolMetadata;
import lombok.extern.slf4j.Slf4j;

/**
 * Register the DataSourcePoolMetadataProvider instances for the supported data sources.
 *
 * @see org.springframework.boot.autoconfigure.jdbc.metadata.DataSourcePoolMetadataProvidersConfiguration
 * @since 2024/8/11
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class DataSourcePoolMetadataProvidersConfiguration {
    public DataSourcePoolMetadataProvidersConfiguration() {
        log.info("DataSourcePoolMetadataProvidersConfiguration create");
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(DruidDataSource.class)
    static class DruidPoolDataSourceMetadataProviderConfiguration {
        @Bean
        DataSourcePoolMetadataProvider druidPoolDataSourceMetadataProvider() {
            return (dataSource) -> {
                DruidDataSource druidDataSource = DataSourceUnwrapper.unwrap(dataSource, DruidAbstractDataSourceMBean.class,
                        DruidDataSource.class);
                if (druidDataSource != null) {
                    return new DruidDataSourcePoolMetadata(druidDataSource);
                }
                return null;
            };
        }
    }
}
