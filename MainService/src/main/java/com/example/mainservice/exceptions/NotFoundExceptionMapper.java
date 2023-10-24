package com.example.mainservice.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException e) {
        return ErrorBuildResponseUtils.buildResponse(
                Response.Status.NOT_FOUND,
                e.getMessage()
        );
    }
}
