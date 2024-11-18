package com.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.QuestionData;
import com.demo.dto.Token;
import com.demo.implementations.JWTImpl;
import com.demo.model.Question;
import com.demo.model.Space;
import com.demo.repositories.QuestionRepository;
import com.demo.services.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.implementations.PermissionImpl;
import com.demo.repositories.SpaceRepository;


@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    SpaceRepository spaceRepository;

    @Autowired
    PermissionImpl permissionImpl;

    @Autowired
    JWTImpl jwtImpl;

    @GetMapping
    public Page<Question> GetQuestion(@RequestParam String query, @RequestParam long spaceId, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {

        Space space = spaceRepository.findById(spaceId);
    
        if (space == null) {
            return Page.empty();
        }

        return questionService.getQuestion(space, page, size);
    }

    @PostMapping
    public String postMethodName(@RequestBody QuestionData data) {
        
        Question question = questionService.createNewQuestion(data.question(), data.spaceId(), data.userId());
        
        questionRepository.save(question);

        return "question create ok!";
    }

    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable Long id, @RequestParam String token) {

        Token myToken = jwtImpl.validate(token);
    
        if (myToken == null) {
            return "Invalid token"; 
        }

        long userId = myToken.getId();

        Question question = questionRepository.getReferenceById(id);
    
        if (question == null) {
            return "Question not found"; 
        }

        int permissionLevel = permissionImpl.validatePermission(userId, question.getSpace().getId());
    
        if (permissionLevel == 0) {
            return "You don't have permission to do that"; 
        }

        if (question.getUser().getId() != userId && permissionLevel < 2) {
            return "You can only delete your own question or be an admin"; 
        }

        boolean deleted = questionService.deleteQuestion(id);

        if (deleted) {
            return "Delete successful"; 
        }

        return "Failed to delete the question"; 
    }
}
