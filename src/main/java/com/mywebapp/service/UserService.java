package com.mywebapp.service;

import com.mywebapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List getAllUsers();
    User getUserById(Long id);
    void saveUser(User user);
    void deleteUserById(Long id);
    void updateUser(User user);
}
