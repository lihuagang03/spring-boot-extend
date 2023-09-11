package com.spring.boot.mybatis.plus.autoconfigure;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * MybatisPlus 自动配置
 *
 * @author lihuagang
 * @since 2023/6/9
 * @see MybatisPlusAutoConfiguration
 */
@Slf4j
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@ConditionalOnSingleCandidate(DataSource.class)
@EnableConfigurationProperties({MybatisPlusProperties.class, ExtendMybatisPlusProperties.class})
@AutoConfiguration(before = {MybatisPlusAutoConfiguration.class}, after = {DataSourceAutoConfiguration.class})
public class ExtendMybatisPlusAutoConfiguration {

    /**
     * MybatisPlus 配置属性集
     *
     * @see MybatisPlusProperties
     */
    private final ExtendMybatisPlusProperties properties;

    public ExtendMybatisPlusAutoConfiguration(
            ExtendMybatisPlusProperties properties
    ) {
        log.info("create ExtendMybatisPlusAutoConfiguration");
        this.properties = properties;
    }

    @Bean
    @ConditionalOnClass({MybatisPlusInterceptor.class})
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 拦截器
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(properties.getDbType()));
        return interceptor;
    }

    @Bean
    @ConditionalOnClass({MybatisPlusPropertiesCustomizer.class})
    public MybatisPlusPropertiesCustomizer mybatisPlusPropertiesCustomizer() {
        return properties -> {
            MybatisConfiguration configuration = new MybatisConfiguration();
            configuration.setMapUnderscoreToCamelCase(true);
            // 枚举属性转换器
            configuration.setDefaultEnumTypeHandler(MybatisEnumTypeHandler.class);
            properties.setConfiguration(configuration);
        };
    }
}
