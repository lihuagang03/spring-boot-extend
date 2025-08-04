package com.tencent.qq.weixin.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 稳定版接口调用凭据
 *
 * @since 2024/4/27
 */
@Data
public class StableAccessTokenResponse {
    /**
     * 凭证，接口调用凭据
     */
    @JsonProperty("access_token")
    private String accessToken;

    /**
     * 凭证有效时间，单位：秒。
     * 目前是7200秒之内的值。
     */
    @JsonProperty("expires_in")
    private int expiresInTimeSeconds;
}
