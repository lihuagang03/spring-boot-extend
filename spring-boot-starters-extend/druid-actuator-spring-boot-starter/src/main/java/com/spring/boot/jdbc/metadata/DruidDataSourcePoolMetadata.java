package com.spring.boot.jdbc.metadata;

import org.springframework.beans.DirectFieldAccessor;
import org.springframework.boot.jdbc.metadata.AbstractDataSourcePoolMetadata;

import com.alibaba.druid.pool.DruidConnectionHolder;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * Druid数据源的连接池元数据
 *
 * @since 2024/8/11
 * @see org.springframework.boot.jdbc.metadata.AbstractDataSourcePoolMetadata
 * @see org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata
 */
public class DruidDataSourcePoolMetadata extends AbstractDataSourcePoolMetadata<DruidDataSource> {
    /**
     * Create an instance with the data source to use.
     *
     * @param dataSource the data source
     */
    public DruidDataSourcePoolMetadata(DruidDataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Integer getActive() {
//        try {
//            return getDataSource().getPoolingCount();
//        }
//        catch (Exception ex) {
//            return null;
//        }
        return getDataSource().getDataSourceStat().getConnectionStat().getActiveCount();
    }

    @Override
    public Integer getIdle() {
//        try {
//            return (int) getDataSource().getConnectCount();
//        }
//        catch (Exception ex) {
//            return null;
//        }
        return (int) getDataSource().getDataSourceStat().getConnectionStat().getConnectCount();
    }

    private int getEvictCount() {
        return ((DruidConnectionHolder[]) new DirectFieldAccessor(getDataSource()).getPropertyValue("evictConnections")).length;
    }

    @Override
    public Integer getMax() {
        return getDataSource().getMaxActive();
    }

    @Override
    public Integer getMin() {
        return getDataSource().getMinIdle();
    }

    @Override
    public String getValidationQuery() {
        return getDataSource().getValidationQuery();
    }

    @Override
    public Boolean getDefaultAutoCommit() {
        return getDataSource().isDefaultAutoCommit();
    }
}
