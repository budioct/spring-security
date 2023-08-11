package com.tutorial.springsecuritytest;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testAuthenticationExceptionUnauthorized() throws Exception {

        mockMvc.perform(
                get("/api/test")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpectAll(
                status().isUnauthorized()
        );

        // 401 Unauthorized

    }

    @Test
    @WithMockUser(authorities = "wrong")
    void testAuthenticationExceptionForbidden() throws Exception {

        mockMvc.perform(
                get("/api/test")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpectAll(
                status().isForbidden()
        );

        // 403 Forbbiden

    }

    @Test
    @WithMockUser(authorities = "read") // Authority // implement object HttpSecurity, for allow requestMatchers endpoint with hasAuthority
    void testAuthenticationCorrectWithAuthority() throws Exception {

        mockMvc.perform(
                get("/api/test")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Test!"))
        );

        // 200 Ok

    }

}
