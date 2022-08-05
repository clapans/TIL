package com.ssafy.api.service;


import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.Question;
import com.ssafy.db.entity.Script;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.QuestionRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ScriptServiceTest {

    @Autowired ScriptService scriptService;
    @Autowired UserService userService;

    @Autowired
    QuestionRepository questionRepository;

    @Test
    public void 스크립트생성() throws Exception {
        //given
        User user = createUser();
        Question question = createQuestion();
        String scriptContent="content";
        String scriptAudioUrl="scriptAudio";

        //when
        Script script = scriptService.createScript(user, question,scriptContent , scriptAudioUrl);

        //then
        assertThat(script.getUser().getId()).isEqualTo(user.getId());
        assertThat(script.getQuestion().getId()).isEqualTo(question.getId());
        assertThat(script.getScriptContent()).isEqualTo(scriptContent);
        assertThat(script.getAudioUrl()).isEqualTo(scriptAudioUrl);
    }
    @Test
    void 스크립트_전체_조회() throws Exception {
        //given
        User user = createUser();
        Question question = createQuestion();
        String scriptContent="content";
        String scriptAudioUrl="scriptAudio";

        //when
        Script script1 = scriptService.createScript(user, question,scriptContent , scriptAudioUrl);
        Script script2 = scriptService.createScript(user, question,scriptContent , scriptAudioUrl);
        List<Script> scriptList = scriptService.getScriptList();

        //then
        assertThat(scriptList.size()).isEqualTo(2);

    }

    @Test
    void 스크립트_디테일_조회() throws Exception {
        //given
        User user = createUser();
        Question question = createQuestion();
        String scriptContent="content";
        String scriptAudioUrl="scriptAudio";

        //when
        Script script = scriptService.createScript(user, question,scriptContent , scriptAudioUrl);
        Long scriptId=script.getId();

        Script findScript = scriptService.getDetail(scriptId).get();
        //then
        assertThat(script).isEqualTo(findScript);
    }
    public User createUser() {
        UserRegisterPostReq userRegisterPostReq=new UserRegisterPostReq();
        userRegisterPostReq.setUsername("user");
        userRegisterPostReq.setPassword("password");
        userRegisterPostReq.setEmail("user@naver.com");
        userRegisterPostReq.setNickname("nickname");
        userRegisterPostReq.setRole("student");

        return userService.createUser(userRegisterPostReq);
    }

    public Question createQuestion() {
        Question question = new Question();
        question.setTopic("자전거타기");
        question.setLevel("IH");
        question.setQuestionContent("asdf");
        question.setAudioUrl("questionAudio.com");

        return questionRepository.save(question);
    }

}