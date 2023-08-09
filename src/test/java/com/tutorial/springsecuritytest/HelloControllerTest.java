package com.tutorial.springsecuritytest;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("""
            when calling the /hello endpoint Authenticated we should get back Hello in the response body, and an http status of 200 OK.
            """)
    @WithMockUser // creates a mock security context
    void testHelloAuthenticated() throws Exception {

        mockMvc.perform(
                get("/hello")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello World!"))
        );
    }

    @Test
    @DisplayName("""
            when calling the /hello endpoint Unauthenticated we should get back Hello in the response body, and an http status of 401 Unauthorized.
            """)
    void testHelloUnauthenticated() throws Exception {

        mockMvc.perform(
                get("/hello")
        ).andExpectAll(
                status().isUnauthorized()
        );
    }

}
