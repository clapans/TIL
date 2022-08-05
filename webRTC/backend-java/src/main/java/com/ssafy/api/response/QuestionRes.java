package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("QuestionResponse")
public class QuestionRes extends BaseResponseBody {
    @ApiModelProperty(name="id", example="0")
    private Long id;

    @ApiModelProperty(name="토픽", example="자전거타기")
    private String topic;

    @ApiModelProperty(name="질문내용", example="내용")
    private String questionContent;

    @ApiModelProperty(name="난이도", example="IL")
    private String level;

    @ApiModelProperty(name="오디오 url", example="url")
    private String audioUrl;
}
