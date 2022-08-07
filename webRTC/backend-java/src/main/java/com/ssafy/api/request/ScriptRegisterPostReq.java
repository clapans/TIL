package com.ssafy.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("ScriptRegisterPostReq")
public class ScriptRegisterPostReq {
    @NotNull(message = "유저 id는 Null 일 수 없습니다.")
    @ApiModelProperty(name="유저 ID", example="1")
    Long userId;

    @NotNull(message = "질문 id는 Null 일 수 없습니다.")
    @ApiModelProperty(name="문제 ID", example="1")
    Long questionId;

    @NotBlank(message = "스크립트 내용은 비어있을 수 없습니다.")
    @ApiModelProperty(name="스크립트 내용", example="I am groot")
    String scriptContent;

    @NotBlank(message = "오디오 URL은 비어있을 수 없습니다.")
    @ApiModelProperty(name="오디오 URL", example="오디오 URL")
    String audioURL;
}
