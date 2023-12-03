package com.example.mainservice.repositories;

import com.example.mainservice.entity.StarShip;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class StarShipRepository {
    @PersistenceContext
    private EntityManager em;

    public StarShip getById(Long id) {
        return em.find(StarShip.class, id);
    }

    @Transactional
    public void save(StarShip starShip) {
        em.persist(starShip);
        em.flush();
    }
}
