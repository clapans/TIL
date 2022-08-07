package com.ssafy.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("QuestionRandomGetRequest")
public class QuestionRandomGetReq {
    @NotBlank
    @ApiModelProperty(name="topic", example="자전거타기")
    String topic;

    @NotBlank
    @ApiModelProperty(name="level", example="IL")
    String level;
}
