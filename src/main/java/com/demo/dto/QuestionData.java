package com.demo.dto;

public record QuestionData(
    String question,
    long userId,
    long spaceId 
) {} 
