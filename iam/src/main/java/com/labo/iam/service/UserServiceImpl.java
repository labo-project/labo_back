package com.labo.iam.service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labo.iam.dto.UserDTO;
import com.labo.iam.repo.IUserRepo;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepo repo;

    @Autowired
    private Mapper mapper;

    @Override
    public Optional<UserDTO> findById(Long id) {
        return this.repo.findById(id)
            .map(user -> mapper.map(user, UserDTO.class));
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return this.repo.findByUsername(username)
            .map(user -> mapper.map(user, UserDTO.class));
    }
}
