package com.example.mainservice.controllers;

import com.example.mainservice.exceptions.InvalidParamException;
import com.example.mainservice.services.interfaces.SpaceMarineService;
import model.request.SpaceMarineBuildDto;
import model.response.ListSpaceMarine;
import model.response.SpaceMarineResponseDto;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpaceMarineController {
    @Inject
    private SpaceMarineService spaceMarineService;

    @GET
    @Path("/spacemarines/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpaceMarineById(@PathParam("id") Long id) {
        return Response.ok().entity(spaceMarineService.getSpaceMarineById(id)).build();
    }

    @GET
    @Path("/spacemarines")
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
    @Path("/spacemarines")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSpaceMarine(SpaceMarineBuildDto spaceMarineBuildDto) {
        SpaceMarineResponseDto spaceMarine = spaceMarineService.createSpaceMarine(spaceMarineBuildDto);
        return Response.ok().entity(spaceMarine).build();
    }

    @PUT
    @Path("/spacemarines/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSpaceMarine(@PathParam("id") Long id, SpaceMarineBuildDto spaceMarineBuildDto) {
        SpaceMarineResponseDto spaceMarine = spaceMarineService.updateSpaceMarine(id, spaceMarineBuildDto);
        return Response.ok().entity(spaceMarine).build();
    }

    @DELETE
    @Path("/spacemarines/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSpaceMarineById(@PathParam("id") Long id) {
        spaceMarineService.deleteSpaceMarine(id);
        return Response.ok().build();
    }
}
