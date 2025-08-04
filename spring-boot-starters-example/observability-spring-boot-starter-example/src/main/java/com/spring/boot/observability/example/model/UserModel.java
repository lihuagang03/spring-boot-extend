package com.spring.boot.observability.example.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户
 *
 * @since 2023/12/16
 */
@Data
@Accessors(chain = true)
public class UserModel {

    /**
     * 用户身份
     */
    private Long id;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
