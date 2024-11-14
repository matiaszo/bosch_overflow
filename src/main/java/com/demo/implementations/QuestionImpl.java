package com.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.model.Question;
import com.demo.model.Space;
import com.demo.repositories.QuestionRepository;
import com.demo.repositories.SpaceRepository;
import com.demo.services.QuestionService;

public class QuestionImpl implements QuestionService {

    @Autowired
    SpaceRepository spaceRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Question createNewQuestion(String question, long spaceId) {

        Space space = spaceRepository.findById(spaceId);

        Question pergunta = new Question();

        pergunta.setQuestion(question);
        pergunta.setSpace(space);

        questionRepository.save(pergunta);
        
        return pergunta;
    }

    @Override
    public boolean deleteQuestion(long idQuestion) {
        
        Question question = questionRepository.findById(idQuestion);

        if (question == null) {
            return false;
        }
        
        return true;
    }
    
}
