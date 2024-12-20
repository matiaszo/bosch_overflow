package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    List<Permission> findByUserIdAndSpaceId(Long userId, Long spaceId);

}

