package com.spring.boot.redis.example.model;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户模型
 *
 * @author guang.yi
 * @since 2023/11/27
 */
@Data
@Accessors(chain = true)
public class UserDto implements Serializable {

    private static final long serialVersionUID = -5221097649619578016L;

    /**
     * 用户id
     */
    private Long id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 昵称
     */
    private String nickName;

}
