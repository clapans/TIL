package com.ssafy.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.api.service.ConsultService;
import com.ssafy.api.service.FeedbackService;
import com.ssafy.api.service.ScriptService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetailService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Feedback;
import com.ssafy.db.entity.Script;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FeedbackController.class)
class FeedbackControllerTest {

    @MockBean
    UserService userService;

    @MockBean
    SsafyUserDetailService ssafyUserDetailService;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    FeedbackService feedbackService;

    @MockBean
    ScriptService scriptService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 피드백추가성공() throws Exception {
        HashMap<String, String> input = new HashMap<>();
        input.put("content", "content");
        input.put("scriptId", "1");
        given(feedbackService.overlapCheck(1L)).willReturn(false);
        given(scriptService.getScriptByScriptId(1L)).willReturn(Optional.of(new Script()));
        mockMvc.perform(post("/api/v1/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(input))
                )
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void 피드백추가실패() throws Exception {
        HashMap<String, String> input = new HashMap<>();
        input.put("content", "content");
        input.put("scriptId", "1");
        given(feedbackService.overlapCheck(1L)).willReturn(false);
        given(scriptService.getScriptByScriptId(1L)).willReturn(Optional.ofNullable(null));
        mockMvc.perform(post("/api/v1/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(input))
                )
                .andExpect(status().is4xxClientError());
    }

    @Test
    void 전체피드백조회() throws Exception {
        mockMvc.perform(get("/api/v1/feedback"))
                .andExpect(status().isOk());
    }

    @Test
    void 피드백디테일조회() throws Exception {
        given(feedbackService.getDetail(1L)).willReturn(Optional.of(new Feedback()));
        mockMvc.perform(get("/api/v1/feedback/1"))
                .andExpect(status().isOk());
    }

    @Test
    void 피드백삭제() throws Exception {
        given(feedbackService.delete(1L)).willReturn(BaseResponseBody.of(200, "삭제 성공"));
        mockMvc.perform(delete("/api/v1/feedback/1"))
                .andExpect(status().isOk());
    }
}