package com.labo.iam.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labo.iam.repo.model.Role;

public interface IRoleRepo extends JpaRepository<Role,Long>{
    Optional<Role> findByName(String name);
    
}
