package com.example.mainservice.controllers;

import com.example.mainservice.exceptions.ErrorBuildResponseUtils;
import com.example.mainservice.exceptions.InvalidParamException;
import com.example.mainservice.exceptions.NotFoundException;
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

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<?> notFoundException(NotFoundException e) {
        return ErrorBuildResponseUtils.buildResponse(
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );
    }
}
