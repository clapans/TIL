package com.ssafy.api.controller;

import com.ssafy.api.request.ConsultRegisterPostReq;
import com.ssafy.api.response.ConsultListRes;
import com.ssafy.api.service.ConsultService;
import com.ssafy.api.service.ScriptService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Consult;
import com.ssafy.db.entity.Script;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value = "상담 API", tags = {"Consult."})
@RestController
@RequestMapping("/api/v1/consult")
@RequiredArgsConstructor
public class ConsultController {
    private final ConsultService consultService;
    private final ScriptService scriptService;

    @PostMapping
    @ApiOperation(value = "상담 신청", notes = "<strong>스크립트</strong>를 가지고 상담신청을 한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 500, message = "스크립트 없음")
    })
    public ResponseEntity<? extends BaseResponseBody> register(
            @RequestBody @ApiParam(value="상담신청 정보", required = true) @Validated ConsultRegisterPostReq consultRegisterPostReq) {
        Long scriptId = consultRegisterPostReq.getScriptId();
        Optional<Script> script = scriptService.getScriptByScriptId(scriptId);
        String room = consultRegisterPostReq.getRoom();

        // 스크립트가 있다면
        if (script.isPresent()) {
        // 스크립트가 있다면 상담 생성
        consultService.create(script.get(), room);
        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success created consult"));
        }
        // 스크립트 없으면 noSuchElementExHandler 호출
        return ResponseEntity.status(404).body(BaseResponseBody.of(404, "Script does not exist"));
    }

    @GetMapping
    @ApiOperation(value = "대기중인 상담 조회", notes = "대기중인 상담 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "대기중인 상담 목록 조회 성공", response = ConsultListRes.class),
    })
    public ResponseEntity<ConsultListRes> getWaitingList() {
        List<Consult> waitingList = consultService.waitingList();
        return ResponseEntity.status(200).body(ConsultListRes.of(200,"Success to get consult list", waitingList));
    }

    @PutMapping("/complete/{consultId}")
    @ApiOperation(value = "상담 상태 변경", notes = "상담 상태를 완료로 변경한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "상담 없음"),
            @ApiResponse(code = 400, message = "이미 변경된 상태")
    })
    public ResponseEntity<? extends BaseResponseBody> complete(@PathVariable Long consultId) {

//        if (consultService.exist(consultId)){
        // 이미 진행완료된 상담 service 단에서 에러 던짐
//        if (consultService.completedStateByConsult(consultId)) {
//            return ResponseEntity.status(500).body(BaseResponseBody.of(500,"Already modified"));
//        }
        // 상담 무사히 완료
        consultService.modifyState(consultId);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Consult completed!"));
//        }
//        // 없는 상담 noSuchElementExHandler 호출
//        return ResponseEntity.status(404).body(BaseResponseBody.of(404, "Consult does not exist"));
//    }
    }
}