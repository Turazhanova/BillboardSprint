package com.example.billboardproject.service;

import com.example.billboardproject.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoleByRoleName(String role);
}
