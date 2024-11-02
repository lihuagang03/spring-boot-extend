package com.tencent.qq.weixin.domain.value;

import lombok.Value;

/**
 * 账号唯一凭证密钥，即 AppSecret，获取方式同 AppId
 *
 * @since 2024/4/27
 */
@Value
public class AppSecret {
    /**
     * 账号唯一凭证密钥
     */
    String appSecret;

    public AppSecret(String appSecret) {
        if (appSecret == null || appSecret.isEmpty()) {
            throw new IllegalArgumentException("appSecret must not be empty");
        }
        this.appSecret = appSecret;
    }
}
