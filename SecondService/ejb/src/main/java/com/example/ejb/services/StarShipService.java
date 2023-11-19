package com.example.ejb.services;

import javax.ejb.Remote;

@Remote
public interface StarShipService {
    Integer unloadAll(Long id);

    Integer createNewStarShip(Long id, String name, String coordX, String coordY, String crewCount, String health);

    Integer check();
}
