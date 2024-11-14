package com.demo.services;

import com.demo.model.Question;

public interface QuestionService {
    public Question createNewQuestion(String question, long spaceId);
    public boolean deleteQuestion(long id);
}
