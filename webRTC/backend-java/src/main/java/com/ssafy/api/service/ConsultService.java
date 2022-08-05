package com.ssafy.api.service;

import com.ssafy.api.request.ConsultRegisterPostReq;
import com.ssafy.db.entity.Consult;
import com.ssafy.db.entity.Script;

import java.util.List;
import java.util.Optional;


public interface ConsultService {
    Consult create(Script script, String room);

    List<Consult> waitingList();

    boolean completedStateByConsult(Long consultId);

    Consult modifyState(Long consultId);

    boolean exist(Long consultId);

    Optional<Consult> getConsultByConsultId(Long consultId);
}