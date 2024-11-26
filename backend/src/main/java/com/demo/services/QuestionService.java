package com.demo.services;

import java.util.ArrayList;
import com.demo.dto.QuestionGet;
import com.demo.dto.Token;
import com.demo.model.Question;

public interface QuestionService {
    public Question createNewQuestion(Token token, String question, long spaceId);
    public boolean deleteQuestion(long id);
    public Question getQuestionById(long id);
    // Page<Question> getQuestion(Long space, int page, int size);
    ArrayList<QuestionGet> getQuestion(Long spaceId, Integer page, Integer limit);

}
