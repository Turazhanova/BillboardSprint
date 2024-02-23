package com.example.billboardproject.service.impl;

import com.example.billboardproject.model.Role;
import com.example.billboardproject.repository.RoleRepository;
import com.example.billboardproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getRoleByRoleName(String role) {
        return roleRepository.findAllByRole(role);
    }
}
