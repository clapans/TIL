package com.ssafy.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.api.service.ConsultService;
import com.ssafy.api.service.ScriptService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetailService;
import com.ssafy.db.entity.Script;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsultController.class)
class ConsultControllerTest {

    @MockBean
    UserService userService;

    @MockBean
    SsafyUserDetailService ssafyUserDetailService;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    ConsultService consultService;

    @MockBean
    ScriptService scriptService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 상담생성() throws Exception {
        HashMap<String, String> input = new HashMap<>();
        input.put("scriptId", "1");
        HashMap<String, String> output = new HashMap<>();
        output.put("message", "Success created consult");
        given(scriptService.getScriptByScriptId(1L)).willReturn(Optional.of(new Script()));
        mockMvc.perform(post("/api/v1/consult")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(input))
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(objectMapper.writeValueAsString(output)));
    }

    @Test
    void 상담생성실패() throws Exception {
        HashMap<String, String> input = new HashMap<>();
        input.put("scriptId", "1");
        HashMap<String, String> output = new HashMap<>();
        output.put("message", "Script does not exist");
        given(scriptService.getScriptByScriptId(1L)).willReturn(Optional.ofNullable(null));
        mockMvc.perform(post("/api/v1/consult")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(input))
                )
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(output)));
    }

    @Test
    void 대기중인상담조회() throws Exception {
        HashMap<String, String> output = new HashMap<>();
        output.put("message", "Success to get consult list");
        mockMvc.perform(get("/api/v1/consult"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(output)));
    }

    @Test
    void 상담상태변경성공() throws Exception {
        HashMap<String, String> output = new HashMap<>();
        output.put("message", "Consult completed!");
        given(consultService.exist(1L)).willReturn(true);
        given(consultService.completedStateByConsult(1L)).willReturn(false);
        mockMvc.perform(put("/api/v1/consult/complete/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(output)));
    }

    @Test
    void 이미진행완료된상담() throws Exception {
        HashMap<String, String> output = new HashMap<>();
        output.put("message", "Already modified");
        given(consultService.exist(1L)).willReturn(true);
        given(consultService.completedStateByConsult(1L)).willReturn(true);
        mockMvc.perform(put("/api/v1/consult/complete/1"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json(objectMapper.writeValueAsString(output)));
    }

    @Test
    void 없는상담() throws Exception {
        HashMap<String, String> output = new HashMap<>();
        output.put("message", "Consult does not exist");
        given(consultService.exist(1L)).willReturn(false);
        mockMvc.perform(put("/api/v1/consult/complete/1"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(objectMapper.writeValueAsString(output)));
    }
}