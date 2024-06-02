package org.apache.skywalking.apm.agent.core.base64;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Test of {@link org.apache.skywalking.apm.agent.core.base64.Base64}
 *
 * @since 2024/6/2
 */
class Base64Test {
    @Test
    void encode() {
        String text = "cyborg-flow";
        String encode = Base64.encode(text);
        assertThat(encode).isEqualTo("Y3lib3JnLWZsb3c=");
    }

    @Test
    void decode2UTFString() {
        String in = "dHJ1ZQ==";
        String decode2UTFString = Base64.decode2UTFString(in);
        assertThat(decode2UTFString).isEqualTo("true");
    }
}
