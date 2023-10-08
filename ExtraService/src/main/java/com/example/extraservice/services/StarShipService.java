package com.example.extraservice.services;

import model.request.StarShipCreateDto;

public interface StarShipService {
    void unloadAll(Long id);

    void createNewStarShip(Long id, String name, StarShipCreateDto createDto);
}
