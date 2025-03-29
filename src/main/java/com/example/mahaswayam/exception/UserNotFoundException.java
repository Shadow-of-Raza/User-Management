package com.example.mahaswayam.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message) {
        super(message);
    }
}