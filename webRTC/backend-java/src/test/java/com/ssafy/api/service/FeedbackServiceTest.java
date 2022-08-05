package com.ssafy.api.service;

import com.ssafy.db.entity.*;
import com.ssafy.db.repository.FeedbackRepository;
import com.ssafy.db.repository.QuestionRepository;
import com.ssafy.db.repository.ScriptRepository;
import com.ssafy.db.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FeedbackServiceTest {

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ScriptRepository scriptRepository;

    @Test
    @DisplayName("피드백생성 성공!")
    public void 피드백생성() throws Exception {
        User user = createUser();
        Question question = createQuestion();
        Script script = createScript(user, question);
        Feedback feedback = feedbackService.create("feedbackContent", script);
        // 피드백이 잘 만들어졌는지
        assertThat(feedback).isEqualTo(feedbackRepository.findById(feedback.getId()).get());
        // 스크립트가 맞게 들어갔는지
        assertThat(script).isEqualTo(feedbackRepository.findById(feedback.getId()).get().getScript());
    }

    @Test
    @DisplayName("피드백디테일조회 성공!")
    public void 피드백디테일조회() throws Exception{
        //Given
        User user = createUser();
        Question question = createQuestion();
        Script script = createScript(user, question);
        Feedback feedback = feedbackService.create("feedbackContent", script);
        //When
        Optional<Feedback> feedbackDetail = feedbackService.getDetail(feedback.getId());
        //Then
        assertThat(feedback.getId()).isEqualTo(feedbackDetail.get().getId());
    }

    @Test
    @DisplayName("전체피드백조회 성공!")
    public void 전체피드백조회() throws Exception {
        //Given
        User user = createUser();
        userRepository.save(user);
        Question question = createQuestion();
        questionRepository.save(question);
        Script script = createScript(user, question);
        scriptRepository.save(script);
        Feedback feedback1 = feedbackService.create("feedbackContent1", script);
        Feedback feedback2 = feedbackService.create("feedbackContent2", script);
        //When
        List<Feedback> feedbackList = feedbackService.getList();
        //Then
        assertThat(feedbackList).contains(feedback1, feedback2);
    }


    @Test
    @DisplayName("피드백삭제 성공!")
    public void 피드백삭제() throws Exception {
        //Given
        User user = createUser();
        Question question = createQuestion();
        Script script = createScript(user, question);
        Feedback feedback = feedbackService.create("feedbackContent", script);
        //When
        feedbackService.delete(feedback.getId());
        //Then
        assertThat(feedbackRepository.findById(feedback.getId())).isEmpty();
    }


    private User createUser() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("password");
        user.setEmail("user@email.com");
        user.setNickname("nickname");
        user.setRole("student");
        return user;
    }

    private Question createQuestion() {
        Question question = new Question();
        question.setTopic("topic");
        question.setQuestionContent("questionContent");
        question.setLevel("level");
        question.setAudioUrl("questionAudioUrl");
        return question;
    }

    private Script createScript(User user, Question question) {
        Script script = new Script();
        script.setUser(user);
        script.setQuestion(question);
        script.setScriptContent("scriptContent");
        script.setAudioUrl("scriptAudioUrl");
        return script;
    }
}