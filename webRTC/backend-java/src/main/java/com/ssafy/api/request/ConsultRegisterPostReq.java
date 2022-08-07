package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("ConsultRegisterPostRequest")
public class ConsultRegisterPostReq {
    @NotNull
    @ApiModelProperty(name="스크립트 ID", example="1")
    Long scriptId;

    @NotBlank
    @ApiModelProperty(name="room", example="randomRoomNumber")
    String room;
}