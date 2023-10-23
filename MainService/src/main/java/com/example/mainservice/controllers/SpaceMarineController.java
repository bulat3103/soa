package com.example.mainservice.controllers;

import com.example.mainservice.entity.AvailableFields;
import com.example.mainservice.entity.FilterOperation;
import com.example.mainservice.entity.SortOperation;
import com.example.mainservice.exceptions.InvalidParamException;
import com.example.mainservice.model.Filter;
import com.example.mainservice.model.Sort;
import com.example.mainservice.model.request.SpaceMarineBuildDto;
import com.example.mainservice.model.response.SpaceMarineResponseDto;
import com.example.mainservice.services.interfaces.SpaceMarineService;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/spacemarines")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SpaceMarineController {
    @Inject
    private SpaceMarineService spaceMarineService;

    @GET
    @Path("/{id}")
    public Response getSpaceMarineById(@PathParam("id") String idStr) {
        long id;
        try {
            id = Long.parseLong(idStr);
            return Response.ok().entity(spaceMarineService.getSpaceMarineById(id)).build();
        } catch (NumberFormatException e) {
            throw new InvalidParamException("Validation failed");
        }
    }

    @GET
    public Response getAllSpaceMarines(@Context HttpServletRequest request) {
        String[] sortParameters = request.getParameterValues("sort");
        String[] filterParameters = request.getParameterValues("filter");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        int pageInt, pageSizeInt;
        try {
            pageInt = StringUtils.isEmpty(page) ? 1 : Integer.parseInt(page);
            if (pageInt <= 0) throw new NumberFormatException();
        } catch (NumberFormatException exception) {
            throw new InvalidParamException("Validation failed");
        }
        try {
            pageSizeInt = StringUtils.isEmpty(pageSize) ? 20 : Integer.parseInt(pageSize);
            if (pageSizeInt <= 0) throw new NumberFormatException();
        } catch (NumberFormatException exception) {
            throw new InvalidParamException("Validation failed");
        }
        List<String> sort;
        if (sortParameters == null) sort = new ArrayList<>();
        else sort = Stream.of(sortParameters).filter(StringUtils::isNotEmpty).collect(Collectors.toList());
        List<String> filter;
        if (filterParameters == null) filter = new ArrayList<>();
        else filter = Stream.of(filterParameters).filter(StringUtils::isNotEmpty).collect(Collectors.toList());
        List<SpaceMarineResponseDto> spaceMarines = spaceMarineService.getAllSpaceMarines(
                getSortParameters(sort),
                getFilterParameters(filter),
                pageInt,
                pageSizeInt);
        return Response.ok().entity(spaceMarines).build();
    }

    @POST
    public Response createSpaceMarine(SpaceMarineBuildDto spaceMarineBuildDto) {
        SpaceMarineResponseDto spaceMarine = spaceMarineService.createSpaceMarine(spaceMarineBuildDto);
        return Response.ok().entity(spaceMarine).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateSpaceMarine(@PathParam("id") String idStr, SpaceMarineBuildDto spaceMarineBuildDto) {
        long id;
        try {
            id = Long.parseLong(idStr);
            SpaceMarineResponseDto spaceMarine = spaceMarineService.updateSpaceMarine(id, spaceMarineBuildDto);
            return Response.ok().entity(spaceMarine).build();
        } catch (NumberFormatException exception) {
            throw new InvalidParamException("Validation failed");
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSpaceMarineById(@PathParam("id") String idStr) {
        Map<String, Object> map = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        long id;
        try {
            id = Long.parseLong(idStr);
            spaceMarineService.deleteSpaceMarine(id);
            map.put("code", 200);
            map.put("message", "The marine was successfully deleted");
            map.put("time", ZonedDateTime.now(ZoneOffset.UTC).format(formatter));
            return Response.ok().entity(map).build();
        } catch (NumberFormatException exception) {
            throw new InvalidParamException("Invalid id value");
        }
    }

    private List<Sort> getSortParameters(List<String> list) {
        List<String> sortOps = Stream.of(SortOperation.values()).map(Enum::toString).collect(Collectors.toList());
        List<Sort> sorts = new ArrayList<>();
        for (String sortP : list) {
            String[] split = sortP.split("=");
            if (split.length != 2) {
                throw new InvalidParamException("Validation failed");
            }
            if (!AvailableFields.checkContains(split[0])) {
                throw new InvalidParamException("Validation failed");
            }
            if (!sortOps.contains(split[1].toUpperCase())) {
                throw new InvalidParamException("Validation failed");
            }
            sorts.add(new Sort(
                    AvailableFields.getByName(split[0]),
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
                if (!AvailableFields.checkContains(field)) {
                    throw new InvalidParamException("Validation failed");
                }
                if (!FilterOperation.checkContains(op.toUpperCase())) {
                    throw new InvalidParamException("Validation failed");
                }
                checkNumberFields(field, value);
                filters.add(new Filter(
                        AvailableFields.getByName(field),
                        FilterOperation.valueOf(op.toUpperCase()),
                        value
                ));
            } else {
                throw new InvalidParamException("Validation failed");
            }
        }
        return filters;
    }

    private void checkNumberFields(String field, String value) {
        if (field.equals(AvailableFields.HEART_COUNT.getName())
                || field.equals(AvailableFields.CHAPTER_MARINES_COUNT.getName())
                || field.equals(AvailableFields.STARSHIP_CREW_COUNT.getName())
                || field.equals(AvailableFields.STARSHIP_HEALTH.getName()))
        {
            try {
                Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new InvalidParamException("Validation failed");
            }
        }
        if (field.equals(AvailableFields.ID.getName())
                || field.equals(AvailableFields.STARSHIP.getName())
                || field.equals(AvailableFields.COORDINATE_X.getName())
                || field.equals(AvailableFields.STARSHIP_COORDINATE_X.getName()))
        {
            try {
                Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new InvalidParamException("Validation failed");
            }
        }
        if (field.equals(AvailableFields.COORDINATE_Y.getName())
                || field.equals(AvailableFields.HEALTH.getName())
                || field.equals(AvailableFields.STARSHIP_COORDINATE_Y.getName()))
        {
            try {
                Double.parseDouble(value);
            } catch (NumberFormatException e) {
                throw new InvalidParamException("Validation failed");
            }
        }
    }
}
