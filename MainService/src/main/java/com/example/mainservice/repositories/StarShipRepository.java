package com.example.mainservice.repositories;

import com.example.mainservice.entity.StarShip;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class StarShipRepository {
    @Inject
    private ManagerProvider managerProvider;

    public StarShip getById(Long id) {
        return managerProvider.getEm().find(StarShip.class, id);
    }

    public void save(StarShip starShip) {
        managerProvider.getEm().persist(starShip);
        managerProvider.getEm().flush();
    }
}
