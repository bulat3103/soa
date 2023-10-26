package com.example.mainservice.controllers;

import com.example.mainservice.entity.AvailablePatternFields;
import com.example.mainservice.exceptions.InvalidParamException;
import com.example.mainservice.model.response.UniqueHeart;
import com.example.mainservice.exceptions.NotFoundException;
import com.example.mainservice.services.interfaces.ExtraFunctionsService;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.ws.rs.*;
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
    public Response getLowerAchieves(@QueryParam("achieve") String achieve) {
        if (StringUtils.isEmpty(achieve) || StringUtils.isBlank(achieve)) {
            throw new InvalidParamException("Validation failed");
        }
        return Response.ok().entity(extraFunctionsService.getLowerAchieves(achieve)).build();
    }

    @GET
    @Path("/pattern")
    public Response getByPattern(@QueryParam("field") String field, @QueryParam("value") String value) {
        if (StringUtils.isEmpty(field)) {
            throw new InvalidParamException("Validation failed");
        } else {
            List<String> values = Stream.of(AvailablePatternFields.values())
                    .map(Enum::toString)
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
            if (!values.contains(field.toLowerCase())) {
                throw new InvalidParamException("Validation failed");
            }
        }
        if (StringUtils.isEmpty(value)) {
            throw new InvalidParamException("Validation failed");
        }
        return Response.ok().entity(extraFunctionsService.getByPattern(field, value)).build();
    }

    @GET
    @Path("/unique/heart")
    public Response getUniqueHeart() {
        UniqueHeart uniqueHeartCount = extraFunctionsService.getUniqueHeartCount();
        if (uniqueHeartCount.getHearts().isEmpty()) {
            throw new NotFoundException("Not found");
        }
        return Response.ok().entity(extraFunctionsService.getUniqueHeartCount().getHearts()).build();
    }
}
