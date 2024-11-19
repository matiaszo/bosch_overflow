package com.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.AnswerData;
import com.demo.dto.QuestionData;
import com.demo.dto.Token;
import com.demo.implementations.JWTImpl;
import com.demo.model.Answer;
import com.demo.model.Question;
import com.demo.model.Space;
import com.demo.repositories.AnswerRepository;
import com.demo.repositories.QuestionRepository;
import com.demo.services.AnswerService;
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
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @Autowired
    AnswerRepository answerRepository;


    @PostMapping
    public String postAnswer(@RequestBody AnswerData data) {
        
        Answer answer = answerService.createNewAnswer(data.questionId(), data.answer());
        
        answerRepository.save(answer);

        return "answer create ok!";
    }

}
