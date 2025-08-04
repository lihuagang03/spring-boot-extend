package com.spring.boot.meeting.service.impl;

import com.spring.boot.meeting.autoconfigure.condition.EdgeService;
import com.spring.boot.meeting.service.MeetingRoomService;
import com.spring.boot.meeting.service.MeetingService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 边侧数据源的会议服务实现
 *
 * @author guang.yi
 * @since 2023/6/13
 */
@Slf4j
@EdgeService
@Service
public class EdgeMeetingServiceImpl implements MeetingService {
    @Resource
    private MeetingRoomService meetingRoomService;

    public EdgeMeetingServiceImpl() {
        log.info("create EdgeMeetingServiceImpl");
    }

    @Override
    public String getAuthSessionId() {
        log.info("Edge getAuthSessionId()");
        return meetingRoomService.getMeetingRoomName(0L);
    }
}
