package com.example.mainservice.controllers;

import com.example.mainservice.catalog.*;
import com.example.mainservice.entity.AvailableFields;
import com.example.mainservice.entity.FilterOperation;
import com.example.mainservice.entity.SortOperation;
import com.example.mainservice.exceptions.*;
import com.example.mainservice.model.Filter;
import com.example.mainservice.model.Sort;
import com.example.mainservice.services.interfaces.SpaceMarineService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Endpoint
public class SpaceMarineController {
    private static final String NAMESPACE_URI = "http://com/example/marineservice/catalog";
    private final SpaceMarineService spaceMarineService;

    @Autowired
    public SpaceMarineController(SpaceMarineService spaceMarineService) {
        this.spaceMarineService = spaceMarineService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSpaceMarineRequest")
    @ResponsePayload
    public GetSpaceMarineResponse getSpaceMarineById(@RequestPayload GetSpaceMarineRequest request) {
        long id;
        try {
            id = Long.parseLong(request.getId());
            SpaceMarineResponseDto spaceMarineById = spaceMarineService.getSpaceMarineById(id);
            GetSpaceMarineResponse response = new GetSpaceMarineResponse();
            response.setId(spaceMarineById.getId());
            response.setName(spaceMarineById.getName());
            response.setCreationDate(spaceMarineById.getCreationDate());
            response.setCoordinates(spaceMarineById.getCoordinates());
            response.setHealth(spaceMarineById.getHealth());
            response.setHeartCount(spaceMarineById.getHeartCount());
            response.setAchievements(spaceMarineById.getAchievements());
            response.setCategory(spaceMarineById.getCategory());
            response.setChapter(spaceMarineById.getChapter());
            response.setStarship(spaceMarineById.getStarship());
            return response;
        } catch (NumberFormatException e) {
            throw new ServiceFaultException("Error", new ServiceFault("400", "Validation failed", ErrorBuildResponseUtils.getTime()));
        } catch (NotFoundException e) {
            throw new ServiceFaultException("Error", new ServiceFault("404", "Not found", ErrorBuildResponseUtils.getTime()));
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSpaceMarinesRequest")
    @ResponsePayload
    public GetSpaceMarinesResponse getAllSpaceMarines(@RequestPayload GetSpaceMarinesRequest request) {
        String[] sortParameters = request.getSort().toArray(String[]::new);
        String[] filterParameters = request.getFilter().toArray(String[]::new);
        String page = request.getPage();
        String pageSize = request.getPageSize();
        int pageInt, pageSizeInt;
        try {
            pageInt = StringUtils.isEmpty(page) ? 1 : Integer.parseInt(page);
            if (pageInt <= 0) throw new NumberFormatException();
        } catch (NumberFormatException exception) {
            throw new ServiceFaultException("Error", new ServiceFault("400", "Validation failed", ErrorBuildResponseUtils.getTime()));
        }
        try {
            pageSizeInt = StringUtils.isEmpty(pageSize) ? 20 : Integer.parseInt(pageSize);
            if (pageSizeInt <= 0) throw new NumberFormatException();
        } catch (NumberFormatException exception) {
            throw new ServiceFaultException("Error", new ServiceFault("400", "Validation failed", ErrorBuildResponseUtils.getTime()));
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
        GetSpaceMarinesResponse response = new GetSpaceMarinesResponse();
        response.getSpaceMarineResponseDtos().addAll(spaceMarines);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createSpaceMarineRequest")
    @ResponsePayload
    public CreateSpaceMarineResponse createSpaceMarine(@RequestPayload CreateSpaceMarineRequest request) {
        try {
            SpaceMarineResponseDto spaceMarine = spaceMarineService.createSpaceMarine(request);
            CreateSpaceMarineResponse response = new CreateSpaceMarineResponse();
            response.setId(spaceMarine.getId());
            response.setName(spaceMarine.getName());
            response.setCreationDate(spaceMarine.getCreationDate());
            response.setCoordinates(spaceMarine.getCoordinates());
            response.setHealth(spaceMarine.getHealth());
            response.setHeartCount(spaceMarine.getHeartCount());
            response.setAchievements(spaceMarine.getAchievements());
            response.setCategory(spaceMarine.getCategory());
            response.setChapter(spaceMarine.getChapter());
            response.setStarship(spaceMarine.getStarship());
            return response;
        } catch (InvalidParamException e) {
            throw new ServiceFaultException("Error", new ServiceFault("400", "Validation failed", ErrorBuildResponseUtils.getTime()));
        } catch (NotFoundException e) {
            throw new ServiceFaultException("Error", new ServiceFault("404", "Not found", ErrorBuildResponseUtils.getTime()));
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateSpaceMarineRequest")
    @ResponsePayload
    public UpdateSpaceMarineResponse updateSpaceMarine(@RequestPayload UpdateSpaceMarineRequest request) {
        long id;
        try {
            id = Long.parseLong(request.getId());
            SpaceMarineResponseDto spaceMarine = spaceMarineService.updateSpaceMarine(id, request);
            UpdateSpaceMarineResponse response = new UpdateSpaceMarineResponse();
            response.setId(spaceMarine.getId());
            response.setName(spaceMarine.getName());
            response.setCreationDate(spaceMarine.getCreationDate());
            response.setCoordinates(spaceMarine.getCoordinates());
            response.setHealth(spaceMarine.getHealth());
            response.setHeartCount(spaceMarine.getHeartCount());
            response.setAchievements(spaceMarine.getAchievements());
            response.setCategory(spaceMarine.getCategory());
            response.setChapter(spaceMarine.getChapter());
            response.setStarship(spaceMarine.getStarship());
            return response;
        } catch (NumberFormatException | InvalidParamException e) {
            throw new ServiceFaultException("Error", new ServiceFault("400", "Validation failed", ErrorBuildResponseUtils.getTime()));
        } catch (NotFoundException e) {
            throw new ServiceFaultException("Error", new ServiceFault("404", "Not found", ErrorBuildResponseUtils.getTime()));
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteSpaceMarineRequest")
    @ResponsePayload
    public DeleteSpaceMarineResponse deleteSpaceMarineById(@RequestPayload DeleteSpaceMarineRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        long id;
        try {
            id = Long.parseLong(request.getId());
            spaceMarineService.deleteSpaceMarine(id);
            DeleteSpaceMarineResponse response = new DeleteSpaceMarineResponse();
            response.setCode("200");
            response.setMessage("The marine was successfully deleted");
            response.setTime(ZonedDateTime.now(ZoneOffset.UTC).format(formatter));
            return response;
        } catch (NumberFormatException exception) {
            throw new ServiceFaultException("Error", new ServiceFault("400", "Validation failed", ErrorBuildResponseUtils.getTime()));
        } catch (NotFoundException e) {
            throw new ServiceFaultException("Error", new ServiceFault("404", "Not found", ErrorBuildResponseUtils.getTime()));
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
