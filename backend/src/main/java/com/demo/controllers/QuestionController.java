package com.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.QuestionData;
import com.demo.dto.QuestionGet;
import com.demo.dto.QuestionSingleGet;
import com.demo.dto.Token;
import com.demo.implementations.JWTImpl;
import com.demo.model.Question;
import com.demo.model.Space;
import com.demo.model.User;
import com.demo.repositories.QuestionRepository;
import com.demo.services.QuestionService;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.implementations.PermissionImpl;
import com.demo.repositories.SpaceRepository;
import com.demo.repositories.UserRepository;


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
    UserRepository userRepository;

    @Autowired
    JWTImpl jwtImpl;

    @GetMapping("/{id}")
    public QuestionSingleGet GetQuestionById(@PathVariable Long id) {

        Question question = questionService.getQuestionById(id);

        Optional<User> getUser = userRepository.findById(question.getUser().getId());

        Optional<Space> getSpace = spaceRepository.findById(question.getSpace().getId());

        User user = getUser.get();
        Space space = getSpace.get(); 

        return new QuestionSingleGet(user.getName(), question.getQuestion(), space.getTitle());
    }

    @GetMapping
    public ArrayList<QuestionGet> GetQuestion(@RequestParam String query, @RequestParam long spaceId, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {

        return questionService.getQuestion(spaceId, page, size);
    }


    @PostMapping
    public String postMethodName(@RequestAttribute("token") Token token, @RequestBody QuestionData data) {
        
        Question question = questionService.createNewQuestion(token, data.question(), data.spaceId());

        if (question == null) {
            return "Erro ao criar a pergunta!";
        }

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
