package com.spring.boot.meeting.service.impl;

import com.spring.boot.meeting.autoconfigure.condition.CloudService;
import com.spring.boot.meeting.service.MeetingRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 云侧数据源的会议室服务实现
 *
 * @author lihuagang
 * @since 2023/6/13
 */
@Slf4j
@CloudService
@Service("cloudMeetingRoomService")
public class CloudMeetingRoomServiceImpl implements MeetingRoomService {

    public CloudMeetingRoomServiceImpl() {
        log.info("create CloudMeetingRoomServiceImpl");
    }

    @Override
    public String getMeetingRoomName(Long id) {
        return "cloudMeetingRoom";
    }
}
