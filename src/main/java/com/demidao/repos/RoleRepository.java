package com.demidao.repos;

import com.demidao.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>, JpaRepository<Role, Long> {

    Role save(Role role);

    List<Role> findAll();

    @Override
    void delete(Role role);

    Role findByRole(String role);
}
