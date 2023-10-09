package com.example.mainservice.controllers;

import com.example.mainservice.entity.AvailableFilterFields;
import com.example.mainservice.entity.AvailableSortFields;
import com.example.mainservice.entity.FilterOperation;
import com.example.mainservice.entity.SortOperation;
import com.example.mainservice.exceptions.InvalidParamException;
import com.example.mainservice.model.Filter;
import com.example.mainservice.model.Sort;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        int pageInt = 1, pageSizeInt = 20;
        try {
            pageInt = StringUtils.isEmpty(page) ? 1 : Integer.parseInt(page);
            if (pageInt < 0) throw new NumberFormatException();
        } catch (NumberFormatException exception) {
            errorMessage.append("Invalid page parameter");
        }
        try {
            pageSizeInt = StringUtils.isEmpty(pageSize) ? 20 : Integer.parseInt(pageSize);
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
        List<SpaceMarineResponseDto> spaceMarines = spaceMarineService.getAllSpaceMarines(
                getSortParameters(sort),
                getFilterParameters(filter),
                pageInt,
                pageSizeInt);
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

    private List<Sort> getSortParameters(List<String> list) {
        List<String> sortOps = Stream.of(SortOperation.values()).map(e -> e.toString().toLowerCase()).collect(Collectors.toList());
        List<Sort> sorts = new ArrayList<>();
        for (String sortP : list) {
            String[] split = sortP.split("=");
            if (split.length != 2) {
                throw new InvalidParamException("Invalid sort parameter");
            }
            if (!AvailableSortFields.checkContains(split[0])) {
                throw new InvalidParamException("Illegal sort field");
            }
            if (!sortOps.contains(split[1])) {
                throw new InvalidParamException("Illegal sort operation");
            }
            sorts.add(new Sort(
                    AvailableSortFields.getByName(split[0]),
                    SortOperation.valueOf(split[1].toUpperCase())));
        }
        return sorts;
    }

    private List<Filter> getFilterParameters(List<String> list) {
        Pattern pattern = Pattern.compile("(.*)\\[(.*)\\]=(.*)");
        List<Filter> filters = new ArrayList<>();
        for (String filter : list) {
            Matcher matcher = pattern.matcher(filter);
            if (matcher.find()) {
                String field = matcher.group(1), op = matcher.group(2), value = matcher.group(3);
                if (!AvailableFilterFields.checkContains(field)) {
                    throw new InvalidParamException("Invalid filter parameter");
                }
                if (!FilterOperation.checkContains(op)) {
                    throw new InvalidParamException("Invalid filter operation");
                }
                if (field.equals(AvailableFilterFields.NAME.getName())
                        || field.equals(AvailableFilterFields.ACHIEVEMENTS.getName())
                        || field.equals(AvailableFilterFields.CATEGORY.getName()))
                {
                    if (!op.equalsIgnoreCase(FilterOperation.EQ.toString())
                            && !op.equalsIgnoreCase(FilterOperation.NE.toString()))
                    {
                        throw new InvalidParamException("Only EQ and NE allowed for string fields");
                    }
                }
                filters.add(new Filter(
                        AvailableFilterFields.getByName(field),
                        FilterOperation.valueOf(op.toUpperCase()),
                        value
                ));
            } else {
                throw new InvalidParamException("Invalid filter parameters");
            }
        }
        return filters;
    }
}
