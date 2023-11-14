package com.example.miepro.service.impl;

import com.example.miepro.config.security.token.AccessTokenEncoder;
import com.example.miepro.config.security.token.impl.AccessTokenImpl;
import com.example.miepro.exception.InvalidCredentialsException;
import com.example.miepro.model.LoginRequest;
import com.example.miepro.model.LoginResponse;
import com.example.miepro.model.User;
import com.example.miepro.repository.UserRepository;
import com.example.miepro.service.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());

        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public String generateAccessToken(User user) {
        Set<String> hardcodedRoles = new HashSet<>(Arrays.asList("ADMIN"));

        return accessTokenEncoder.encode(
                new AccessTokenImpl(user.getUsername(), hardcodedRoles));
    }
}
