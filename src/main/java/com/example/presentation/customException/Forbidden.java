package com.example.presentation.customException;

public class Forbidden extends RuntimeException {
    Forbidden(String message) {
        super(message);
    }
}

