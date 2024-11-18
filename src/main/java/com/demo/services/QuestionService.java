package com.demo.services;

import org.springframework.data.domain.Page;

import com.demo.model.Question;
import com.demo.model.Space;

public interface QuestionService {
    public Question createNewQuestion(String question, long spaceId, long userId);
    public boolean deleteQuestion(long id);
    public Question getQuestionById(long id);
    Page<Question> getQuestion(Space space, int page, int size);
}
