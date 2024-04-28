package com.tencent.qq.weixin.domain.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link StableAccessTokenRequest}
 *
 * @since 2024/4/28
 */
class StableAccessTokenRequestTest {

    @Test
    void toJsonString() throws JsonProcessingException {
        String appId = "123456:appId";
        String appSecret = "654321:appSecret";
        StableAccessTokenRequest request = new StableAccessTokenRequest(appId, appSecret);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(request);
        assertThat(jsonString)
                .isEqualTo("{\"grant_type\":\"client_credential\",\"appid\":\"123456:appId\",\"secret\":\"654321:appSecret\",\"force_refresh\":false}");
    }
}
