package com.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.model.Space;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {

    @Query("SELECT u FROM Space u WHERE u.title LIKE %:searchValue% OR u.description LIKE %:searchValue%")
    Page<Space> searchSpace(@Param("searchValue") String searchValue, Pageable pageable);

    List<Space> findByTitle(String title);
    Space findById(long id);

    // ! CÓDIGO DA EQUIPE DE INTEGRAÇÃO
    public List<Space> findByTitleContains(String title, PageRequest req);
}
