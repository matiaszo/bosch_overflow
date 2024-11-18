package com.demo.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.model.Permission;

import com.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.name = :searchValue or u.email = :searchValue or u.edv = :searchValue")
    List<User> searchUser(@Param("searchValue") String searchValue);

    @Query("SELECT u FROM Permission u WHERE u.user = :searchValue")
    List<Permission> searchPermissions(@Param("searchValue") Long searchValue);

    @Query("SELECT u FROM Permission u WHERE u.user = :searchValue and u.space = :searchSpace")
    List<Permission> searchPermissionsSpaces(@Param("searchValue") Long searchValue, @Param("searchSpace") Long searchSpace);

    @Query("SELECT u FROM User u WHERE u.edv = :searchValue")
    List<User> searchEdv(@Param("searchValue") String searchValue);

    User findById(long id);
}
