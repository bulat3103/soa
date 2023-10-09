package com.example.mainservice.controllers;

import com.example.mainservice.exceptions.InvalidParamException;
import com.example.mainservice.model.request.SpaceMarineBuildDto;
import com.example.mainservice.model.response.ListSpaceMarine;
import com.example.mainservice.model.response.SpaceMarineResponseDto;
import com.example.mainservice.services.interfaces.SpaceMarineService;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/spacemarines")
public class SpaceMarineController {
    @Inject
    private SpaceMarineService spaceMarineService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpaceMarineById(@PathParam("id") String idStr) {
        long id;
        try {
            id = Long.parseLong(idStr);
            return Response.ok().entity(spaceMarineService.getSpaceMarineById(id)).build();
        } catch (NumberFormatException e) {
            throw new InvalidParamException("Id should be long type!");
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSpaceMarines(@Context HttpServletRequest request) {
        String[] sortParameters = request.getParameterValues("sort");
        String[] filterParameters = request.getParameterValues("filter");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        StringBuilder errorMessage = new StringBuilder();
        int pageInt = 0, pageSizeInt = 0;
        try {
            pageInt = StringUtils.isEmpty(page) ? 0 : Integer.parseInt(page);
            if (pageInt < 0) throw new NumberFormatException();
        } catch (NumberFormatException exception) {
            errorMessage.append("Invalid page parameter");
        }
        try {
            pageSizeInt = StringUtils.isEmpty(pageSize) ? 0 : Integer.parseInt(pageSize);
            if (pageSizeInt < 0) throw new NumberFormatException();
        } catch (NumberFormatException exception) {
            errorMessage.append("Invalid pageSize parameter");
        }
        List<String> sort;
        if (sortParameters == null) sort = new ArrayList<>();
        else sort = Stream.of(sortParameters).filter(StringUtils::isNotEmpty).collect(Collectors.toList());
        List<String> filter;
        if (filterParameters == null) filter = new ArrayList<>();
        else filter = Stream.of(filterParameters).filter(StringUtils::isNotEmpty).collect(Collectors.toList());
        if (errorMessage.length() != 0) {
            throw new InvalidParamException(errorMessage.toString());
        }
        List<SpaceMarineResponseDto> spaceMarines = spaceMarineService.getAllSpaceMarines(sort, filter, pageInt, pageSizeInt);
        return Response.ok().entity(new ListSpaceMarine(spaceMarines)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSpaceMarine(@Valid SpaceMarineBuildDto spaceMarineBuildDto) {
        SpaceMarineResponseDto spaceMarine = spaceMarineService.createSpaceMarine(spaceMarineBuildDto);
        return Response.ok().entity(spaceMarine).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSpaceMarine(@PathParam("id") String idStr, @Valid SpaceMarineBuildDto spaceMarineBuildDto) {
        long id;
        try {
            id = Long.parseLong(idStr);
            SpaceMarineResponseDto spaceMarine = spaceMarineService.updateSpaceMarine(id, spaceMarineBuildDto);
            return Response.ok().entity(spaceMarine).build();
        } catch (NumberFormatException exception) {
            throw new InvalidParamException("Id should be long type!");
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSpaceMarineById(@PathParam("id") String idStr) {
        long id;
        try {
            id = Long.parseLong(idStr);
            spaceMarineService.deleteSpaceMarine(id);
            return Response.ok().build();
        } catch (NumberFormatException exception) {
            throw new InvalidParamException("Id should be long type!");
        }
    }
}
