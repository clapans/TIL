package com.ssafy.api.service;

import com.ssafy.api.request.ScriptRegisterPostReq;
import com.ssafy.db.entity.Question;
import com.ssafy.db.entity.Script;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.QuestionRepository;
import com.ssafy.db.repository.ScriptRepository;
import com.ssafy.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("scriptService")
@Transactional
@RequiredArgsConstructor
public class ScriptServiceImpl implements ScriptService {

    private final ScriptRepository scriptRepository;

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @Override
    public Script createScript(User user, Question question, String content, String audioUrl) {
        Script script = new Script();

        script.setUser(user);
        script.setQuestion(question);
        script.setScriptContent(content);
        script.setAudioUrl(audioUrl);


        return scriptRepository.save(script);
    }

    @Override
    public List<Script> getScriptList() { return scriptRepository.findAll();}

    @Override
    public Optional<Script> getDetail(Long scriptId) {return scriptRepository.findById(scriptId);}

    @Override
    public void deleteByScriptId(Long scriptId) {
        scriptRepository.deleteById(scriptId);

    }

    @Override
    public Optional<Script> getScriptByScriptId(Long scriptId) {
        return scriptRepository.findById(scriptId);
    }


}
