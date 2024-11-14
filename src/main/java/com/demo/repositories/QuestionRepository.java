package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findById(long id);
}
