package com.labo.iam.security;

import org.springframework.stereotype.Service;

import com.labo.iam.repo.IRoleRepo;
import com.labo.iam.repo.model.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final IRoleRepo roleRepository;

    public boolean hasPermission(String roleName, String permission) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));

        return role.getPermissions().stream()
                .anyMatch(p -> p.getName().equals(permission));
    }
}
