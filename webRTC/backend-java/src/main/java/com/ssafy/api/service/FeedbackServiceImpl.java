package com.ssafy.api.service;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Feedback;
import com.ssafy.db.entity.Script;
import com.ssafy.db.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Transactional
    public Feedback create(String content, Script script){
        Feedback feedback = new Feedback();
        feedback.setContent(content);
        feedback.setScript(script);
        return feedbackRepository.save(feedback);
    }

    public BaseResponseBody delete(Long feedbackId){
        feedbackRepository.deleteById(feedbackId);
        return BaseResponseBody.of(200,"삭제 성공");
    }

    @Transactional(readOnly = true)
    public List<Feedback> getList(){
        return feedbackRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Feedback> getDetail(Long feedbackId){
        return feedbackRepository.findById(feedbackId);
    }

    @Transactional(readOnly = true)
    public boolean overlapCheck(Long scriptId){
        return feedbackRepository.findByScriptId(scriptId).isPresent();
    }
}