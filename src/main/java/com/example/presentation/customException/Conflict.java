package com.example.presentation.customException;

public class Conflict extends RuntimeException {
    Conflict(String message) {
        super(message);
    }
}