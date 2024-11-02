package com.tencent.qq.weixin.domain.value;

import lombok.Value;

/**
 * 账号唯一凭证，即 AppId，可在「微信公众平台 - 设置 - 开发设置」页中获得。
 * （需要已经成为开发者，且账号没有异常状态）
 *
 * @since 2024/4/27
 */
@Value
public class AppId {
    /**
     * 账号唯一凭证
     */
    String appId;

    public AppId(String appId) {
        if (appId == null || appId.isEmpty()) {
            throw new IllegalArgumentException("appId must not be empty");
        }
        this.appId = appId;
    }
}
