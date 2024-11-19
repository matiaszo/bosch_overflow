package com.demo.services;

import com.demo.model.Answer;

public interface AnswerService {
    public Answer createNewAnswer(long questionId, String answer);
}
