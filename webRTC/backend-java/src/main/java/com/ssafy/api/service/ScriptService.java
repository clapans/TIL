package com.ssafy.api.service;

import com.ssafy.api.request.ScriptRegisterPostReq;
import com.ssafy.db.entity.Question;
import com.ssafy.db.entity.Script;
import com.ssafy.db.entity.User;

import java.util.List;
import java.util.Optional;

public interface ScriptService {
    Script createScript(User user, Question question, String content, String audioUrl);

    Optional<Script> getScriptByScriptId(Long scriptId);
    List<Script> getScriptList();

    Optional<Script> getDetail(Long script_id);
    void deleteByScriptId(Long scriptId);
}
