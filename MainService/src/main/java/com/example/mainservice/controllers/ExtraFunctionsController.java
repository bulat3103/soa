package com.example.mainservice.controllers;

import com.example.mainservice.catalog.*;
import com.example.mainservice.entity.AvailablePatternFields;
import com.example.mainservice.exceptions.*;
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
            throw new ServiceFaultException("Error", new ServiceFault("400", "Validation failed", ErrorBuildResponseUtils.getTime()));
        }
        GetLowerAchievesResponse response = new GetLowerAchievesResponse();
        response.setCount(extraFunctionsService.getLowerAchieves(request.getAchieve()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getByPatternRequest")
    @ResponsePayload
    public GetByPatternResponse getByPattern(@RequestPayload GetByPatternRequest request) {
        if (StringUtils.isEmpty(request.getField())) {
            throw new ServiceFaultException("Error", new ServiceFault("400", "Validation failed", ErrorBuildResponseUtils.getTime()));
        } else {
            List<String> values = Stream.of(AvailablePatternFields.values())
                    .map(Enum::toString)
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
            if (!values.contains(request.getField().toLowerCase())) {
                throw new ServiceFaultException("Error", new ServiceFault("400", "Validation failed", ErrorBuildResponseUtils.getTime()));
            }
        }
        if (StringUtils.isEmpty(request.getValue())) {
            throw new ServiceFaultException("Error", new ServiceFault("400", "Validation failed", ErrorBuildResponseUtils.getTime()));
        }
        List<SpaceMarineResponseDto> byPattern = extraFunctionsService.getByPattern(request.getField(), request.getValue());
        GetByPatternResponse response = new GetByPatternResponse();
        response.getSpaceMarineResponseDtos().addAll(byPattern);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUniqueHeartRequest")
    @ResponsePayload
    public GetUniqueHeartResponse getUniqueHeart() {
        GetUniqueHeartResponse response = extraFunctionsService.getUniqueHeartCount();
        if (response.getHearts().isEmpty()) {
            throw new ServiceFaultException("Error", new ServiceFault("404", "Not found", ErrorBuildResponseUtils.getTime()));
        }
        return response;
    }
}
