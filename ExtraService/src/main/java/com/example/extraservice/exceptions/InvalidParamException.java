package com.example.extraservice.exceptions;

public class InvalidParamException extends RuntimeException{
    public InvalidParamException() {
        super();
    }

    public InvalidParamException(String message) {
        super(message);
    }
}
