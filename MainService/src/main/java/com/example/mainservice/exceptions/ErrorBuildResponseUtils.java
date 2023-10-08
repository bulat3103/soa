package com.example.mainservice.exceptions;

import model.Error;

import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ErrorBuildResponseUtils {
    public static Response buildResponse(Response.Status status, String message) {
        Error error = new Error(
                status.getStatusCode(),
                message,
                Timestamp.valueOf(LocalDateTime.now())
        );
        return Response.status(status).entity(error).build();
    }
}
