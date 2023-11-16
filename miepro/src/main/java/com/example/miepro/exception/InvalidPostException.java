package com.example.miepro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidPostException extends ResponseStatusException {
    public InvalidPostException(String errorCause) {
        super(HttpStatus.BAD_REQUEST, errorCause);
    }
}
