package com.demidao.services;

import com.demidao.models.Role;

import java.util.List;

public interface RoleService {

    Role save(Role role);

    void delete(Role role);

    Role findByRole(String role);

    List<Role> findAll();
}
