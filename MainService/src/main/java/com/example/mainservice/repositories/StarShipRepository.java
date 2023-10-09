package com.example.mainservice.repositories;

import com.example.mainservice.entity.StarShip;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class StarShipRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public StarShip getById(Long id) {
        return entityManager.find(StarShip.class, id);
    }

    public void save(StarShip starShip) {
        entityManager.persist(starShip);
        entityManager.flush();
    }
}
