package com.example.mainservice.controllers;

import com.example.mainservice.catalog.*;
import com.example.mainservice.entity.AvailablePatternFields;
import com.example.mainservice.exceptions.InvalidParamException;
import com.example.mainservice.exceptions.NotFoundException;
import com.example.mainservice.services.interfaces.ExtraFunctionsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Endpoint
public class ExtraFunctionsController {
    private static final String NAMESPACE_URI = "http://com/example/marineservice/catalog";
    private final ExtraFunctionsService extraFunctionsService;

    @Autowired
    public ExtraFunctionsController(ExtraFunctionsService extraFunctionsService) {
        this.extraFunctionsService = extraFunctionsService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLowerAchievesRequest")
    @ResponsePayload
    public GetLowerAchievesResponse getLowerAchieves(@RequestPayload GetLowerAchievesRequest request) {
        if (StringUtils.isEmpty(request.getAchieve()) || StringUtils.isBlank(request.getAchieve())) {
            throw new InvalidParamException("Validation failed");
        }
        GetLowerAchievesResponse response = new GetLowerAchievesResponse();
        response.setCount(extraFunctionsService.getLowerAchieves(request.getAchieve()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getByPatternRequest")
    @ResponsePayload
    public GetSpaceMarinesResponse getByPattern(@RequestPayload GetByPatternRequest request) {
        if (StringUtils.isEmpty(request.getField())) {
            throw new InvalidParamException("Validation failed");
        } else {
            List<String> values = Stream.of(AvailablePatternFields.values())
                    .map(Enum::toString)
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
            if (!values.contains(request.getField().toLowerCase())) {
                throw new InvalidParamException("Validation failed");
            }
        }
        if (StringUtils.isEmpty(request.getValue())) {
            throw new InvalidParamException("Validation failed");
        }
        List<SpaceMarineResponseDto> byPattern = extraFunctionsService.getByPattern(request.getField(), request.getValue());
        GetSpaceMarinesResponse response = new GetSpaceMarinesResponse();
        response.getSpaceMarineResponseDtos().addAll(byPattern);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUniqueHeartRequest")
    @ResponsePayload
    public GetUniqueHeartResponse getUniqueHeart() {
        GetUniqueHeartResponse response = extraFunctionsService.getUniqueHeartCount();
        if (response.getHearts().isEmpty()) {
            throw new NotFoundException("Not found");
        }
        return response;
    }
}
