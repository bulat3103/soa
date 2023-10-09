package com.example.extraservice.controllers;

import com.example.extraservice.model.request.StarShipCreateDto;
import com.example.extraservice.services.StarShipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("starship")
public class StartShipController {
    private final StarShipService starShipService;

    public StartShipController(StarShipService starShipService) {
        this.starShipService = starShipService;
    }

    @PostMapping(value = "/create/{id}/{name}")
    public ResponseEntity<?> createNewStarShip(
            @PathVariable("id") Long id,
            @PathVariable("name") String name,
            @RequestBody StarShipCreateDto starShipCreateDto)
    {
        return starShipService.createNewStarShip(id, name, starShipCreateDto);
    }

    @PostMapping(value = "/{id}/unload-all")
    public ResponseEntity<?> unloadAll(@PathVariable("id") Long id) {
        return starShipService.unloadAll(id);
    }
}
