package com.labo.iam.service;

import java.util.Optional;

import com.labo.iam.dto.UserDTO;

public interface IUserService {
    Optional<UserDTO> findById(Long id);
    Optional<UserDTO> findByUsername(String username);
    
}
