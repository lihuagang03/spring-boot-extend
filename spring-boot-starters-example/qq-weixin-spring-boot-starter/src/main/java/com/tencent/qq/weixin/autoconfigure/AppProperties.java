package com.tencent.qq.weixin.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 账号属性
 * <pre>
 * 微信
 * 支付宝
 * 抖音
 * </pre>
 *
 * @since 2024/4/30
 */
@Getter
@Setter
@ToString
public class AppProperties {
    /**
     * 账号名称
     */
    private String appName;

    /**
     * 账号唯一凭证
     */
    private String appId;

    /**
     * 账号唯一凭证密钥
     */
    private String appSecret;
}
