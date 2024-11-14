package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Space;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {

    List<Space> findByTitle(String title);
    Space findById(long id);
}
