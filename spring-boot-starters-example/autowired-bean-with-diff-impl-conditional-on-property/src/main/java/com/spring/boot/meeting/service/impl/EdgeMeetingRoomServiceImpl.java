package com.spring.boot.meeting.service.impl;

import com.spring.boot.meeting.autoconfigure.condition.EdgeService;
import com.spring.boot.meeting.service.MeetingRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 边侧数据源的会议室服务实现
 *
 * @author lihuagang
 * @date 2023/6/13
 */
@Slf4j
@EdgeService
@Service("edgeMeetingRoomService")
public class EdgeMeetingRoomServiceImpl implements MeetingRoomService {

    public EdgeMeetingRoomServiceImpl() {
        log.info("create EdgeMeetingRoomServiceImpl");
    }

    @Override
    public String getMeetingRoomName(Long id) {
        return "edgeMeetingRoom";
    }
}
