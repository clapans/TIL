package com.ssafy.api.service;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Feedback;
import com.ssafy.db.entity.Script;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FeedbackService {
    BaseResponseBody delete(Long feedbackId);
    Feedback create(String content, Script script);
    List<Feedback> getList();
    Optional<Feedback> getDetail(Long feedbackId);

    boolean overlapCheck(Long scriptId);
}

