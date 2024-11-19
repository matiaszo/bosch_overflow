package com.demo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.model.Question;
import com.demo.model.Space;



@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q WHERE q.space.id = :searchValue")
    Page<Question> searchQuestion(@Param("searchValue") Long searchValue, Pageable pageable);

    Question findById(long id);

}

