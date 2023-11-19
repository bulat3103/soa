package com.example.api.exceptions;

import com.example.api.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorBuildResponseUtils {
    public static ResponseEntity<?> buildResponse(HttpStatus status, String message) {
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Error error = new Error(
                status.value(),
                message,
                now.format(formatter)
        );
        return ResponseEntity.status(status).body(error);
    }
}
