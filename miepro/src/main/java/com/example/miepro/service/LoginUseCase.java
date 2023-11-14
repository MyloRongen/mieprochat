package com.example.miepro.service;

import com.example.miepro.model.LoginRequest;
import com.example.miepro.model.LoginResponse;
import com.example.miepro.model.User;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
    String generateAccessToken(User user);
}
