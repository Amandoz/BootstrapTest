package com.mywebapp.service;

import com.mywebapp.model.User;

import java.util.List;

public interface UserService {
    List getAllUsers();
    User getUserById(Long id);
    void saveUser(User user);
    void deleteUserById(Long id);
    void updateUser(User user);
}
