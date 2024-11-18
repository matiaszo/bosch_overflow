package com.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.model.Answer;
import com.demo.model.Question;
import com.demo.repositories.AnswerRepository;
import com.demo.repositories.QuestionRepository;
import com.demo.services.AnswerService;

public class AnswerImpl implements AnswerService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Override
    public Answer createNewAnswer(long questionId, String answer) {
        Question question = questionRepository.findById(questionId).orElse(null);  
    
        if (question == null) {
            return null;
        }
    
        Answer reply = new Answer();
        reply.setAnswer(answer);
        reply.setQuestion(question);
    
        answerRepository.save(reply);
        return reply;
    }
    
}
