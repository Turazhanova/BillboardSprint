package com.example.billboardproject.service;

import com.example.billboardproject.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    List<Role> getRoleByRoleName(String role);
}
