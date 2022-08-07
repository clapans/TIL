package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Feedback;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackRes extends BaseResponseBody {
    @ApiModelProperty(name="피드백", example="0")
    Feedback feedback;

    public static FeedbackRes of(Integer statusCode, String message, Feedback feedback) {
        FeedbackRes res = new FeedbackRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setFeedback(feedback);
        return res;
    }

}
