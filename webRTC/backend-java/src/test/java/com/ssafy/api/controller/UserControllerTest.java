package com.ssafy.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @MockBean
    UserService userService;
    @MockBean
    PasswordEncoder passwordEncoder;
    @MockBean
    SsafyUserDetailService ssafyUserDetailService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void 회원가입성공() throws Exception {
        Map<String, String> input = new HashMap<>();

        input.put("username", "user");
        input.put("password", "password");
        input.put("email", "user2@naver.com");
        input.put("nickname", "nickname");

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(input))
                )
                .andExpect(status().isOk());
    }

    @Test
    void 유효성검증_회원가입_실패() throws Exception {
        Map<String, String> input = new HashMap<>();

        input.put("username", "");
        input.put("password", "password");
        input.put("email", "user2@naver.com");
        input.put("nickname", "nickname");

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(input))
                )
                .andExpect(status().is4xxClientError());
    }


}