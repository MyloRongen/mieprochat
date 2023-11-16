package com.example.miepro.controller;

import com.example.miepro.model.*;
import com.example.miepro.service.LoginUseCase;
import com.example.miepro.service.RefreshTokenService;
import com.example.miepro.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
@RequiredArgsConstructor
public class AuthController {

    private final RefreshTokenService refreshTokenService;
    private final LoginUseCase loginUseCase;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = loginUseCase.login(loginRequest);

        if (loginResponse == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        User user = userService.findUserByUsername(loginRequest.getUsername());

        refreshTokenService.deleteRefreshTokensByUserId(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        JwtResponse jwtResponse = JwtResponse.builder()
                .accessToken(loginResponse.getAccessToken())
                .token(refreshToken.getToken())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(jwtResponse);
    }

    @PostMapping("/refreshToken")
    public JwtResponse refreshToken(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest){
        return refreshTokenService.findByToken(refreshTokenRequest.getToken())
                .map(refreshTokenService::verifyExpirationToken)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = loginUseCase.generateAccessToken(user);
                    return JwtResponse.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequest.getToken())
                            .build();
                }).orElseThrow(() -> new RuntimeException("Refresh token is not in the database!"));
    }
}