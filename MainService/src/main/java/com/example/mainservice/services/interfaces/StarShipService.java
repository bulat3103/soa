package com.example.mainservice.services.interfaces;

import com.example.mainservice.model.request.StarShipCreateDto;

public interface StarShipService {
    void createStarShip(StarShipCreateDto starShipCreateDto);

    void unloadAllFromStarShip(Long id);
}
