package com.example.api.controllers;

import com.example.api.exceptions.ErrorBuildResponseUtils;
import com.example.api.exceptions.InternalServerError;
import com.example.api.exceptions.InvalidParamException;
import com.example.api.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionApiHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidParamException.class)
    protected ResponseEntity<?> invalidParamException(InvalidParamException e) {
        return ErrorBuildResponseUtils.buildResponse(
                HttpStatus.BAD_REQUEST,
                e.getMessage()
        );
    }

    @ExceptionHandler(NumberFormatException.class)
    protected ResponseEntity<?> numberFormatException(NumberFormatException e) {
        return ErrorBuildResponseUtils.buildResponse(
                HttpStatus.BAD_REQUEST,
                "Validation failed"
        );
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<?> notFoundException(NotFoundException e) {
        return ErrorBuildResponseUtils.buildResponse(
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );
    }

    @ExceptionHandler(InternalServerError.class)
    protected ResponseEntity<?> internalServerError(InternalServerError e) {
        return ErrorBuildResponseUtils.buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage()
        );
    }
}
