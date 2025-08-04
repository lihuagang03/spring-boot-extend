package com.spring.boot.meeting.controller;

import com.spring.boot.meeting.util.MockMvcRequestUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link MeetingController}.
 *
 * @since 2023/6/13
 */
@SpringBootTest
@AutoConfigureMockMvc
class CloudMeetingControllerTest {
    private static final Object[] EMPTY_PARAMS = new Object[]{};

    @Resource
    private MockMvc mockMvc;

    @Resource
    private MeetingController meetingController;

    static {
        System.setProperty("meeting.service", "cloud");
    }

    @Test
    void getAuthSession() throws Exception {
        String url = "/meeting/auth/session/get";
        String expectedContent = "cloudMeetingRoom";
        MockMvcRequestUtils.getMock(mockMvc, url, EMPTY_PARAMS,
                expectedContent);

        String response = meetingController.getAuthSession();
        assertThat(response).isEqualTo("cloudMeetingRoom");
    }
}
