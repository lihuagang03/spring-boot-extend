package com.spring.boot.meeting.service;

/**
 * 会议服务
 * <p>
 * "云-边-端"架构
 *
 * @since 2023/6/13
 */
public interface MeetingService {

    // 鉴权

    /**
     * 获取认证会话身份
     *
     * @return 会话身份
     */
    String getAuthSessionId();

}
