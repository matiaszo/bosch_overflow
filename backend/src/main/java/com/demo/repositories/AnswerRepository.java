package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {}
