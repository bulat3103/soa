package com.example.mainservice.controllers;

import com.example.mainservice.exceptions.ErrorBuildResponseUtils;
import com.example.mainservice.exceptions.InvalidParamException;
import com.example.mainservice.exceptions.NotFoundException;
import com.example.mainservice.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(InvalidParamException.class)
    protected ResponseEntity<Error> invalidParamException(InvalidParamException e) {
        return ErrorBuildResponseUtils.buildResponse(
                HttpStatus.BAD_REQUEST,
                e.getMessage()
        );
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Error> notFoundException(NotFoundException e) {
        return ErrorBuildResponseUtils.buildResponse(
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );
    }
}
