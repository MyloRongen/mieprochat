package com.example.miepro.service;

import com.example.miepro.model.User;

public interface UserService {
    User findUserByUsername(String username);
}
