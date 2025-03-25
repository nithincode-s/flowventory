package com.example.presentation.customException;

public class Unauthorized extends RuntimeException {
    Unauthorized(String message) {
        super(message);
    }
}
