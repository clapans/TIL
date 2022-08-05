package com.ssafy.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.api.service.QuestionService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetailService;
import com.ssafy.db.entity.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuestionController.class)
class QuestionControllerTest {
    @MockBean
    QuestionService questionService;
    @MockBean
    UserService userService;
    @MockBean
    PasswordEncoder passwordEncoder;
    @MockBean
    SsafyUserDetailService ssafyUserDetailService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Test
    void 랜덤질문생성_성공() throws Exception {
        Map<String, String> input = new HashMap<>();
        String topic = "자전거타기";
        String level = "IH";
        input.put("topic", topic);
        input.put("level", level);

        given(questionService.getRandomDetail(topic, level)).willReturn(Optional.ofNullable(new Question()));
        mockMvc.perform(get("/api/v1/question/random")
                    .characterEncoding("UTF-8")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .param("level",level)
                                .param("topic",topic)
                )
                .andExpect(status().isOk());
    }

    @Test
    void 랜덤질문생성_실패() throws Exception {
        Map<String, String> input = new HashMap<>();
        String topic = "";
        String level = "IH";
        input.put("topic", topic);
        input.put("level", level);

        given(questionService.getRandomDetail(topic, level)).willReturn(Optional.ofNullable(new Question()));
        mockMvc.perform(get("/api/v1/question/random")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("level",level)
                        .param("topic",topic)
                )
                .andExpect(status().is4xxClientError());
    }
}