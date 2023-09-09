package com.spring.boot.meeting.autoconfigure.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 边侧服务 Property Conditions
 *
 * @author lihuagang
 * @since 2023/6/13
 * @see ConditionalOnProperty
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
//@ConditionalOnProperty
@ConditionalOnProperty(prefix = "meeting", name = "service", havingValue = "edge")
public @interface EdgeService {

//    @AliasFor(annotation = ConditionalOnProperty.class, attribute = "prefix")
//    String prefix() default "meeting";
//
//    @AliasFor(annotation = ConditionalOnProperty.class, attribute = "name")
//    String[] name() default {"service"};
//
//    @AliasFor(annotation = ConditionalOnProperty.class, attribute = "havingValue")
//    String havingValue() default "edge";
}
