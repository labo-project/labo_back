package com.labo.iam.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.labo.iam.dto.UserDTO;
import com.labo.iam.service.IUserService;


@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private IUserService service;

    @GetMapping
    public ResponseEntity<Optional<UserDTO>> getMethodName(@RequestParam("username") String username) {
        return ResponseEntity.ok(this.service.findByUsername(username));
    }
    
}
