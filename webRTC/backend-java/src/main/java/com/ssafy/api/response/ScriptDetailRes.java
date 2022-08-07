package com.ssafy.api.response;


import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Script;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("ScriptDetailResponse")
public class ScriptDetailRes extends BaseResponseBody {
    @ApiModelProperty(name="스크립트 디테일", example="")
    Script script;

    public static ScriptDetailRes of(Integer statusCode, String message, Script script) {
        ScriptDetailRes res = new ScriptDetailRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setScript(script);
        return res;
    }
}
