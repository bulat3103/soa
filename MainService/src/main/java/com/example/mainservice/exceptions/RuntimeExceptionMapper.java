package com.example.mainservice.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable e) {
        return ErrorBuildResponseUtils.buildResponse(
                Response.Status.NOT_FOUND,
                "Not found"
        );
    }
}
