package com.demo.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.model.Permission;

import com.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.name LIKE %:searchValue% OR u.email LIKE %:searchValue% OR u.edv LIKE %:searchValue%")
    Page<User> searchUser(@Param("searchValue") String searchValue, Pageable pageable);

    @Query("SELECT u FROM Permission u WHERE u.user = :searchValue")
    List<Permission> searchPermissions(@Param("searchValue") Long searchValue);

    @Query("SELECT u FROM Permission u WHERE u.user = :searchValue and u.space = :searchSpace")
    List<Permission> searchPermissionsSpaces(@Param("searchValue") Long searchValue, @Param("searchSpace") Long searchSpace);

    List<User> findByEdv(String edv);

    User findById(long id);
}
