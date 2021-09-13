package com.demidao.services;

import com.demidao.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> index();

    User show(long id);

    User findByEmail(String email);

    void save(User user);

    void saveJSON(String user);

    void save(User user, Collection<String> roles);

    boolean update(User newUser, Collection<String> newRoles);

    boolean updateJSON(String user);

    boolean update(User newUser);


    boolean delete(long id);
}
