package com.ssafy.api.controller;


import com.ssafy.api.request.QuestionRandomGetReq;
import com.ssafy.api.response.QuestionRes;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.QuestionService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Question;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(value = "질문 API", tags = {"Question."})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/question")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/random")
    @ApiOperation(value = "랜덤질문", notes = "토픽과 레벨로 랜덤질문을 가져온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = QuestionRes.class),
    })
    public ResponseEntity<QuestionRes> randomQuestion(@ModelAttribute @ApiParam(value="토픽,레벨 정보", required = true) @Validated QuestionRandomGetReq questionRandomGetReq) {
        Question question = questionService.getRandomDetail(questionRandomGetReq.getTopic(), questionRandomGetReq.getLevel()).get();
        QuestionRes questionRes = new QuestionRes();

        questionRes.setStatusCode(200);
        questionRes.setMessage("Success");
        questionRes.setId(question.getId());
        questionRes.setTopic(question.getTopic());
        questionRes.setQuestionContent(question.getQuestionContent());
        questionRes.setLevel(question.getLevel());
        questionRes.setAudioUrl(question.getAudioUrl());

        return ResponseEntity.ok(questionRes);
    }

}
