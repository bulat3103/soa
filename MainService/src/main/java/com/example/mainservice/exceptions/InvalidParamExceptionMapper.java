package com.example.mainservice.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidParamExceptionMapper implements ExceptionMapper<InvalidParamException> {
    @Override
    public Response toResponse(InvalidParamException e) {
        return ErrorBuildResponseUtils.buildResponse(
                Response.Status.BAD_REQUEST,
                e.getMessage()
        );
    }
}
