package com.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.AnswerData;
import com.demo.model.Answer;
import com.demo.repositories.AnswerRepository;
import com.demo.services.AnswerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @Autowired
    AnswerRepository answerRepository;


    @PostMapping
    public String postAnswer(@RequestBody AnswerData data) {
        
        Answer answer = answerService.createNewAnswer(data.questionId(), data.answer());

        if (answer == null) {
            return "Ocorreu um erro ao criar a resposta!"; 
        }

        return "answer create ok!";
    }

}
