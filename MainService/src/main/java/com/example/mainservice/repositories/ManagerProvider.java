package com.example.mainservice.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.ext.Provider;

@Provider
public class ManagerProvider {
    @PersistenceContext
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }
}
