package com.example.miepro.config.security.token;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
