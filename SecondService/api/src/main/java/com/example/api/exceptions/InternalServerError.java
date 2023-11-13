package com.example.api.exceptions;

public class InternalServerError extends RuntimeException {
    public InternalServerError() {
        super();
    }

    public InternalServerError(String message) {
        super(message);
    }
}
