package com.tencent.qq.weixin.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 稳定版接口调用凭据
 *
 * @since 2024/4/27
 */
@Getter
@ToString
@AllArgsConstructor
public class StableAccessTokenRequest {
    @JsonProperty("grant_type")
    private final String grantType = "client_credential";

    /**
     * 账号唯一凭证
     */
    @JsonProperty("appid")
    private String appId;

    /**
     * 账号唯一凭证密钥
     */
    @JsonProperty("secret")
    private String appSecret;

    /**
     * 默认使用 false。
     * 1. force_refresh = false 时为普通调用模式，access_token 有效期内重复调用该接口不会更新 access_token；
     * 2. 当force_refresh = true 时为强制刷新模式，会导致上次获取的 access_token 失效，并返回新的 access_token
     */
    @JsonProperty("force_refresh")
    private final boolean forceRefresh = false;
}
