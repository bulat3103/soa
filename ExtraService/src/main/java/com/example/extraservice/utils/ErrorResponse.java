package com.example.extraservice.utils;

import com.example.extraservice.model.Error;
import org.springframework.http.ResponseEntity;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorResponse {
    public static ResponseEntity<?> buildResponse(int status, String message) {
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Error error = new Error(
                status,
                message,
                now.format(formatter)
        );
        return ResponseEntity.status(status).body(error);
    }
}
