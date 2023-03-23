package com.cjx.exception;

public class IllegalPasswordException extends RuntimeException {
    public IllegalPasswordException(String message) {
        super(message);
    }
}
