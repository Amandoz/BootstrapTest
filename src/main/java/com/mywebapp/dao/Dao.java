package com.mywebapp.dao;

import com.mywebapp.model.User;

import java.util.List;

public interface Dao {
    User getUserById(long id);
    List<User> getAllUser();
    void deleteUserById(long id);
    void updateUser(User user);
    void saveUser(User user);
}
