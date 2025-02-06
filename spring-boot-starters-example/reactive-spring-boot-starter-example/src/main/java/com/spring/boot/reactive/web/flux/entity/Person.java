package com.spring.boot.reactive.web.flux.entity;

import java.time.LocalDateTime;

/**
 * 人们【实体】
 *
 * @author lihuagang
 */
public record Person(String firstName, String lastName, Address address, LocalDateTime localDateTime) {
}
