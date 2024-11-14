package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    
}
