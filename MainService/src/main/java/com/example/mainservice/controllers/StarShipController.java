package com.example.mainservice.controllers;

import com.example.mainservice.services.interfaces.StarShipService;
import model.request.StarShipCreateDto;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class StarShipController {
    @Inject
    private StarShipService starShipService;

    @GET
    @Path("/starship")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStarShip(StarShipCreateDto starShipCreateDto) {
        starShipService.createStarShip(starShipCreateDto);
        return Response.ok().build();
    }
}
