package com.example.mainservice.controllers;

import com.example.mainservice.catalog.CreateStarShipRequest;
import com.example.mainservice.catalog.CreateStarShipResponse;
import com.example.mainservice.exceptions.ErrorBuildResponseUtils;
import com.example.mainservice.exceptions.InvalidParamException;
import com.example.mainservice.exceptions.ServiceFault;
import com.example.mainservice.exceptions.ServiceFaultException;
import com.example.mainservice.services.interfaces.StarShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Endpoint
public class StarShipController {
    private static final String NAMESPACE_URI = "http://com/example/marineservice/catalog";
    private final StarShipService starShipService;

    @Autowired
    public StarShipController(StarShipService starShipService) {
        this.starShipService = starShipService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createStarShipRequest")
    @ResponsePayload
    public CreateStarShipResponse createStarShip(@RequestPayload CreateStarShipRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            starShipService.createStarShip(request);
            CreateStarShipResponse response = new CreateStarShipResponse();
            response.setCode("200");
            response.setMessage("Starship created successfully");
            response.setTime(ZonedDateTime.now(ZoneOffset.UTC).format(formatter));
            return response;
        } catch (InvalidParamException e) {
            throw new ServiceFaultException("Error", new ServiceFault("400", "Validation failed", ErrorBuildResponseUtils.getTime()));
        }
    }
}
