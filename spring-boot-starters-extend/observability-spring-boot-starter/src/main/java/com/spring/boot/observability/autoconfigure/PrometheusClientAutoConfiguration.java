package com.spring.boot.observability.autoconfigure;

import org.springframework.boot.actuate.autoconfigure.metrics.export.prometheus.PrometheusMetricsExportAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;

import lombok.extern.slf4j.Slf4j;

/**
 * Prometheus自动配置
 *
 * @see org.springframework.boot.actuate.autoconfigure.metrics.export.prometheus.PrometheusMetricsExportAutoConfiguration
 */
@Slf4j
@AutoConfiguration(after = {PrometheusMetricsExportAutoConfiguration.class})
public class PrometheusClientAutoConfiguration {
    public PrometheusClientAutoConfiguration() {
        log.info("create PrometheusClientAutoConfiguration");
    }
}
