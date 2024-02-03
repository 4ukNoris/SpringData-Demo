package com.example.springdataintrodemo.services;

import com.example.springdataintrodemo.models.User;

public interface UserService {
    void registerUser(User user);

    User getUserByUsername(String username);
}
