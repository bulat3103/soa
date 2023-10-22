package com.example.mainservice.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException e) {
        return ErrorBuildResponseUtils.buildResponse(
                Response.Status.INTERNAL_SERVER_ERROR,
                "Internal server error"
        );
    }
}
