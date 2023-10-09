package com.example.mainservice.controllers;

import com.example.mainservice.entity.AvailablePatternFileds;
import com.example.mainservice.exceptions.InvalidParamException;
import com.example.mainservice.services.interfaces.ExtraFunctionsService;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/spacemarines")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExtraFunctionsController {
    @Inject
    private ExtraFunctionsService extraFunctionsService;

    @GET
    @Path("/lower-achieves")
    public Response getLowerAchieves(@Context HttpServletRequest request) {
        String achieve = request.getParameter("achieve");
        if (StringUtils.isEmpty(achieve) || StringUtils.isBlank(achieve)) {
            throw new InvalidParamException("Achieve param shouldn't be empty!");
        }
        return Response.ok().entity(extraFunctionsService.getLowerAchieves(achieve)).build();
    }

    @GET
    @Path("/pattern")
    public Response getByPattern(@Context HttpServletRequest request) {
        String field = request.getParameter("field");
        String value = request.getParameter("value");
        StringBuilder errorMessage = new StringBuilder();
        if (StringUtils.isEmpty(field)) {
            errorMessage.append("Field shouldn't be empty");
        } else {
            List<String> values = Stream.of(AvailablePatternFileds.values()).map(Enum::toString).collect(Collectors.toList());
            if (!values.contains(field.toUpperCase())) {
                errorMessage.append("Not available field type");
            }
        }
        if (StringUtils.isEmpty(value)) {
            errorMessage.append("Value shouldn't be empty");
        }
        if (errorMessage.length() != 0) {
            throw new InvalidParamException(errorMessage.toString());
        }
        return Response.ok().entity(extraFunctionsService.getByPattern(field, value)).build();
    }

    @GET
    @Path("/unique/heart")
    public Response getUniqueHeart() {
        return Response.ok().entity(extraFunctionsService.getUniqueHeartCount()).build();
    }
}
