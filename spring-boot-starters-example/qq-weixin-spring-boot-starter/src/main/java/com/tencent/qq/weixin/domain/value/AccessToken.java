package com.tencent.qq.weixin.domain.value;

import lombok.Value;

/**
 * 获取到的凭证，接口调用凭据
 *
 * @since 2024/4/27
 */
@Value
public class AccessToken {
    /**
     * 凭证，接口调用凭据
     */
    String accessToken;

    /**
     * 凭证有效时间，单位：秒。
     * 目前是7200秒之内的值。
     */
    int expiresInTimeSeconds;

    public AccessToken(String accessToken, int expiresInTimeSeconds) {
        if (accessToken == null || accessToken.isEmpty()) {
            throw new IllegalArgumentException("accessToken must not be empty");
        }
        this.accessToken = accessToken;
        if (expiresInTimeSeconds <= 0) {
            throw new IllegalArgumentException("expiresInTimeSeconds must greater than 0");
        }
        this.expiresInTimeSeconds = expiresInTimeSeconds;
    }
}
