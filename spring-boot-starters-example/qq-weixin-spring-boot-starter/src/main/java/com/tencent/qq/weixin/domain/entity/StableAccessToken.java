package com.tencent.qq.weixin.domain.entity;

import com.tencent.qq.weixin.domain.value.AppId;
import com.tencent.qq.weixin.domain.value.AppSecret;

/**
 * <a href="https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/getStableAccessToken.html">
 *     获取稳定版接口调用凭据</a>
 *
 * @since 2024/4/27
 */
public final class StableAccessToken {
    private static final String STABLE_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/stable_token";

    public static StableAccessTokenResponse getStableAccessToken(
            AppId appId, AppSecret appSecret) {
        StableAccessTokenRequest request = new StableAccessTokenRequest(
                appId.getAppId(), appSecret.getAppSecret());
        return getStableAccessToken(request);
    }

    private static StableAccessTokenResponse getStableAccessToken(
            StableAccessTokenRequest request) {
        // ToDo
        return null;
    }
}
