package com.example.presentation.customException;

public class BadRequest extends RuntimeException {
    public BadRequest(String message) {
        super(message);
    }
}