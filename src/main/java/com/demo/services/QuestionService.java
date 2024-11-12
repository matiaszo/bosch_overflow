package com.demo.services;

import com.demo.model.Question;

public interface QuestionService {
    public Question createNewQuestion(String question);
    public boolean deleteQuestion(Long idQuestion);
}
