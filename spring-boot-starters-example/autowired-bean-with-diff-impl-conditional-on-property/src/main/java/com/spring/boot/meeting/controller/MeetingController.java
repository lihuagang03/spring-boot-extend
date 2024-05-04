package com.spring.boot.meeting.controller;

import com.spring.boot.meeting.service.MeetingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 会议控制器
 *
 * @since 2023/6/13
 */
@RestController("meetingController")
@RequestMapping(path = "/meeting")
public class MeetingController {

    @Resource
    private MeetingService meetingService;

    @GetMapping("/auth/session/get")
    public String getAuthSession() {
        return meetingService.getAuthSessionId();
    }
}
