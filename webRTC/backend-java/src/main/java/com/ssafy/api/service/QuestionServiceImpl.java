package com.ssafy.api.service;

import com.ssafy.db.entity.Question;
import com.ssafy.db.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    public Optional<Question> getRandomDetail(String topic,String level) {
        List<Question> findByTopicList = questionRepository.findAllByTopicAndLevel(topic,level);
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int randomIndex = random.nextInt(findByTopicList.size());

        return Optional.of(findByTopicList.get(randomIndex));
    }
    @Override
    public Optional<Question> getQuestionByQuestionId(Long questionId) {
        return questionRepository.findById(questionId);
    }
}
