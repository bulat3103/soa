package com.example.mainservice.exceptions;

import com.example.mainservice.model.Error;

import javax.ws.rs.core.Response;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorBuildResponseUtils {
    public static Response buildResponse(Response.Status status, String message) {
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Error error = new Error(
                status.getStatusCode(),
                message,
                now.format(formatter)
        );
        return Response.status(status).entity(error).build();
    }
}
