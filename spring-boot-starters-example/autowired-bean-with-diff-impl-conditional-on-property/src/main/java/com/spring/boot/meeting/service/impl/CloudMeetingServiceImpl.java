package com.spring.boot.meeting.service.impl;

import com.spring.boot.meeting.autoconfigure.condition.CloudService;
import com.spring.boot.meeting.service.MeetingRoomService;
import com.spring.boot.meeting.service.MeetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 云侧数据源的会议服务实现
 *
 * @author lihuagang
 * @date 2023/6/13
 */
@Slf4j
@CloudService
@Service("cloudMeetingService")
public class CloudMeetingServiceImpl implements MeetingService {

    @Resource
    private MeetingRoomService meetingRoomService;

    public CloudMeetingServiceImpl() {
        log.info("create CloudMeetingServiceImpl");
    }

    @Override
    public String getAuthSessionId() {
        log.info("Cloud getAuthSessionId()");
        return meetingRoomService.getMeetingRoomName(0L);
    }
}
