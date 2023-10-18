package com.example.mainservice.controllers;

import com.example.mainservice.model.request.StarShipCreateDto;
import com.example.mainservice.services.interfaces.StarShipService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/starship")
public class StarShipController {
    @Inject
    private StarShipService starShipService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStarShip(StarShipCreateDto starShipCreateDto) {
        starShipService.createStarShip(starShipCreateDto);
        return Response.ok().build();
    }
}
