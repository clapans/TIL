package com.ssafy.api.service;


import com.ssafy.api.request.ConsultRegisterPostReq;
import com.ssafy.db.entity.Consult;
import com.ssafy.db.entity.Script;
import com.ssafy.db.repository.ConsultRepository;
import com.ssafy.db.repository.ScriptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsultServiceImpl implements ConsultService {

    private final ConsultRepository consultRepository;

    @Override
    public Consult create(Script script, String room) {
        Consult consult = new Consult();
        consult.setScript(script);
        consult.setRoom(room);
        return consultRepository.save(consult);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Consult> waitingList() {
        return consultRepository.findAll().stream().filter(state -> !state.isState()).collect(Collectors.toList());
    }

    @Override
    public Consult modifyState(Long consultId) {
        Consult findConsult = consultRepository.findById(consultId).get();
        if (findConsult.isState()) {
            throw new IllegalArgumentException("이미 진행 완료된 상담입니다!");
        }
        findConsult.setState(true);
        return consultRepository.save(findConsult);
    }

    @Override
    public boolean exist(Long consultId) {
        return consultRepository.findById(consultId).isPresent();
    }

    @Override
    public boolean completedStateByConsult(Long consultId) {
        Consult consult = consultRepository.findById(consultId).get();
        return consult.isState();
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<Consult> getConsultByConsultId(Long consultId) {
        return consultRepository.findById(consultId);
    }
}