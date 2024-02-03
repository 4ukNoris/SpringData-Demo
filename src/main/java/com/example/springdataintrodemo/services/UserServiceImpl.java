package com.example.springdataintrodemo.services;

import com.example.springdataintrodemo.models.User;
import com.example.springdataintrodemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        Optional<User> foundUser = this.userRepository.findByUsername(user.getUsername());
        if (foundUser.isEmpty()) {
            this.userRepository.save(user);
        }
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        return user.orElse(null);
    }
}
