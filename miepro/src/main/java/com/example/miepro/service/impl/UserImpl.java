package com.example.miepro.service.impl;

import com.example.miepro.exception.UserNotFoundException;
import com.example.miepro.model.User;
import com.example.miepro.repository.UserRepository;
import com.example.miepro.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserService {

    private final UserRepository userRepository;

    public UserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByUsername(String username) {
        if (username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException("User not found for username: " + username);
        }

        return user;
    }
}
