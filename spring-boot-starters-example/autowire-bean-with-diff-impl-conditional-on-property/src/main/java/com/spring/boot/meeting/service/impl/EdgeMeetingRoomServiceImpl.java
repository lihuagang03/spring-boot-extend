package com.spring.boot.meeting.service.impl;

import com.spring.boot.meeting.service.MeetingRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * 边侧数据源的会议室服务实现
 *
 * @author lihuagang
 * @date 2023/6/13
 */
@Slf4j
@ConditionalOnProperty(prefix = "meeting", name = "service", havingValue = "edge")
//@Service("meetingRoomService")
@Service("edgeMeetingRoomService")
public class EdgeMeetingRoomServiceImpl implements MeetingRoomService {

    public EdgeMeetingRoomServiceImpl() {
        log.info("create EdgeMeetingRoomServiceImpl instance");
    }

    @Override
    public String getMeetingRoomName(Long id) {
        return "edgeMeetingRoom";
    }
}
