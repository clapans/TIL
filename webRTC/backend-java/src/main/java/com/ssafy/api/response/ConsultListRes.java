package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Consult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("ConsultListResponse")
public class ConsultListRes extends BaseResponseBody {
    @ApiModelProperty(name = "대기중인 상담 목록")
    List<Consult> consultList;

    public static ConsultListRes of(Integer statusCode, String message, List<Consult> consultList) {
        ConsultListRes res = new ConsultListRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setConsultList(consultList);
        return res;
    }
}