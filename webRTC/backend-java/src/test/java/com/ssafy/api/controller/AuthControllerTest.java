package com.ssafy.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.service.UserService;
import com.ssafy.db.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class AuthControllerTest {

    @Autowired
    UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void 로그인_성공() throws Exception {
        String testId="user";
        String testPassword="password1";

        Map<String, String> input = new HashMap<>();
        input.put("username", testId);
        input.put("password", testPassword);

        UserRegisterPostReq userRegisterInfo=new UserRegisterPostReq();
        userRegisterInfo.setUsername("user");
        userRegisterInfo.setPassword("password1");
        userRegisterInfo.setEmail("user@naver.com");
        userRegisterInfo.setNickname("nickname");
        userRegisterInfo.setRole("student");

        //when
        User user=userService.createUser(userRegisterInfo);

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(input))
                )
                .andExpect(status().isOk());
    }

    @Test
    void 유효성검증_로그인_실패() throws Exception {
        Map<String, String> input = new HashMap<>();

        input.put("username", "");
        input.put("password", "password");

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(input))
                )
                .andExpect(status().is4xxClientError());
    }
}