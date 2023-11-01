package com.example.mainservice.controllers;

import com.example.mainservice.model.request.StarShipCreateDto;
import com.example.mainservice.services.interfaces.StarShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/starship")
public class StarShipController {
    private final StarShipService starShipService;

    @Autowired
    public StarShipController(StarShipService starShipService) {
        this.starShipService = starShipService;
    }

    @PostMapping
    public ResponseEntity<?> createStarShip(StarShipCreateDto starShipCreateDto) {
        starShipService.createStarShip(starShipCreateDto);
        return ResponseEntity.ok().build();
    }
}
