package com.example.extraservice.controllers;

import com.example.extraservice.model.request.StarShipCreateDto;
import com.example.extraservice.services.StarShipService;
import com.example.extraservice.utils.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("starship")
public class StartShipController {
    private final StarShipService starShipService;

    public StartShipController(StarShipService starShipService) {
        this.starShipService = starShipService;
    }

    @CrossOrigin
    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello, World!";
    }

    @CrossOrigin
    @PostMapping(value = "/create/{id}/{name}")
    public ResponseEntity<?> createNewStarShip(
            @PathVariable("id") String idStr,
            @PathVariable("name") String name,
            @RequestBody StarShipCreateDto starShipCreateDto)
    {
        long id;
        try {
            id = Long.parseLong(idStr);
            return starShipService.createNewStarShip(id, name, starShipCreateDto);
        } catch (NumberFormatException e) {
            return ErrorResponse.buildResponse(400, "Validation failed");
        }
    }

    @CrossOrigin
    @PostMapping(value = "/{id}/unload-all")
    public ResponseEntity<?> unloadAll(@PathVariable("id") String idStr) {
        long id;
        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            return ErrorResponse.buildResponse(400, "Validation failed");
        }
        return starShipService.unloadAll(id);
    }
}
