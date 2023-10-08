package com.example.extraservice.controllers;

import com.example.extraservice.model.request.StarShipCreateDto;
import com.example.extraservice.services.StarShipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/starship")
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
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{id}/unload-all")
    public ResponseEntity<?> unloadAll(@PathVariable("id") Long id) {
        starShipService.unloadAll(id);
        return ResponseEntity.ok().build();
    }
}
