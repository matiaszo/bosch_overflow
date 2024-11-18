package com.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    @Autowired
    PermissionImpl permissionImpl;

    @Override
    public Question createNewQuestion(String question, long spaceId, long userId) {

    Space space = spaceRepository.findById(spaceId);
    
    if (space == null) {
        return null; 
    }

    var permission = permissionImpl.validatePermission(userId, spaceId);

    if (permission == 0) {
        return null;
    }

    Question questions = new Question();
    questions.setQuestion(question);
    questions.setSpace(space);

    return questions;
}


    @Override
    public boolean deleteQuestion(long idQuestion) {

        Question question = questionRepository.findById(idQuestion).orElse(null);

        if (question == null) {
            return false; 
        }

        questionRepository.delete(question);
        return true; 
    }



    @Override
    public Question getQuestionById(long id) {

    Question question = questionRepository.findById(id).orElse(null);

    return question;
}


    @Override
    public Page<Question> getQuestion(Space space, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        return questionRepository.searchQuestion(space, pageable);
}

    
}
