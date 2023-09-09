package com.spring.boot.meeting.service;

/**
 * 会议室服务
 * <p>
 * "云-边-端"架构
 *
 * @author lihuagang
 * @since 2023/6/13
 */
public interface MeetingRoomService {

    /**
     * 获取会议室名称
     *
     * @param id 会议室id
     * @return 会议室名称
     */
    String getMeetingRoomName(Long id);

}
