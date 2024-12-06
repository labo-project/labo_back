package com.labo.iam.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labo.iam.repo.model.User;

public interface IUserRepo extends JpaRepository<User,Long>{
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
