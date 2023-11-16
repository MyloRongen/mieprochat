package com.example.miepro.service;

import com.example.miepro.model.RefreshToken;
import com.example.miepro.model.User;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(User user);
    Optional<RefreshToken> findByToken(String token);
    RefreshToken verifyExpirationToken(RefreshToken token);
    void deleteRefreshTokensByUserId(User user);
}
