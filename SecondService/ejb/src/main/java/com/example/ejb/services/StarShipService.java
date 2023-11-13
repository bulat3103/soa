package com.example.ejb.services;

public interface StarShipService {
    Integer unloadAll(Long id);

    Integer createNewStarShip(Long id, String name, String coordX, String coordY, String crewCount, String health);
}
