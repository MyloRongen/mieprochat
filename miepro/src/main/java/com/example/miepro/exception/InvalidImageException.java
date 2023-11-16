package com.example.miepro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidImageException extends ResponseStatusException {
    public InvalidImageException(String errorCause) {
        super(HttpStatus.BAD_REQUEST, errorCause);
    }
}
