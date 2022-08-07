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
@ApiModel("ScriptListResponse")
public class ScriptListRes extends BaseResponseBody {
    @ApiModelProperty(name="스크립트 리스트", example="")
    List<Script> scriptList;

    public static ScriptListRes of(Integer statusCode, String message, List<Script> scriptList) {
        ScriptListRes res = new ScriptListRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setScriptList(scriptList);
        return res;
    }
}
