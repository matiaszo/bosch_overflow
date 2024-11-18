package com.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.QuestionData;
import com.demo.model.Question;
import com.demo.repositories.QuestionRepository;
import com.demo.services.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepository;

    @PostMapping
    public String postMethodName(@RequestBody QuestionData data) {
        
        Question question = questionService.createNewQuestion(data.question(), data.spaceId(), data.userId());
        
        questionRepository.save(question);

        return "question create ok!";
    }
    
}