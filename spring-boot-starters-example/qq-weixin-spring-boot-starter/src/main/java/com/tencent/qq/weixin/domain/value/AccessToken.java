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

    public AccessToken(String accessToken) {
        if (accessToken == null || accessToken.isEmpty()) {
            throw new IllegalArgumentException("'accessToken' must not be empty");
        }
        this.accessToken = accessToken;
    }
}
