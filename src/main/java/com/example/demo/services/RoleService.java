package com.example.demo.services;

import com.example.demo.entities.Role;
import com.example.demo.repositories.RoleRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    Role getUserRole(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = roleRepository.findByName("ROLE_USER");
            return role;
        }
        return role;
    }

}
