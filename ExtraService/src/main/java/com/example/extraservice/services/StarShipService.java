package com.example.extraservice.services;

import com.example.extraservice.model.request.StarShipCreateDto;
import org.springframework.http.ResponseEntity;

public interface StarShipService {
    ResponseEntity<?> unloadAll(Long id);

    ResponseEntity<?> createNewStarShip(Long id, String name, StarShipCreateDto createDto);
}
