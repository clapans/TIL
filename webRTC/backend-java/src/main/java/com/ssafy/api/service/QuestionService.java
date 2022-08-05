package com.ssafy.api.service;

import com.ssafy.db.entity.Question;

import java.util.Optional;

public interface QuestionService {
    Optional<Question> getRandomDetail(String topic,String level);

    Optional<Question> getQuestionByQuestionId(Long questionId);
}
