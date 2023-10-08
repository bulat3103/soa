package com.example.mainservice.repositories;

import com.example.mainservice.entity.SpaceMarine;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class SpaceMarineRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public SpaceMarine save(SpaceMarine spaceMarine) {
        entityManager.getTransaction().begin();
        entityManager.persist(spaceMarine);
        entityManager.getTransaction().commit();
        entityManager.flush();
        return spaceMarine;
    }

    public SpaceMarine getById(Long id) {
        return entityManager.find(SpaceMarine.class, id);
    }

    public List<SpaceMarine> getAllSpaceMarines(List<String> sort, List<String> filter, Integer page, Integer limit) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<SpaceMarine> criteriaDelete = criteriaBuilder.createCriteriaDelete(SpaceMarine.class);
        Root<SpaceMarine> from = criteriaDelete.from(SpaceMarine.class);
        return null;
    }

    public void deleteById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<SpaceMarine> criteriaDelete = criteriaBuilder.createCriteriaDelete(SpaceMarine.class);
        Root<SpaceMarine> from = criteriaDelete.from(SpaceMarine.class);
        criteriaDelete.where(criteriaBuilder.equal(from.get("id"), id));
        entityManager.createQuery(criteriaDelete).executeUpdate();
    }

    public List<Integer> getUniqueHeartCountColumn() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> query = criteriaBuilder.createQuery(Integer.class);
        query.select(query.from(SpaceMarine.class).get("heartCount")).distinct(true);
        TypedQuery<Integer> tq = entityManager.createQuery(query);
        return tq.getResultList();
    }

    public Integer getLowerAchieves(String achieve) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SpaceMarine> query = criteriaBuilder.createQuery(SpaceMarine.class);
        Root<SpaceMarine> from = query.from(SpaceMarine.class);
        query.select(from).where(criteriaBuilder.lessThan(from.get("achievements"), achieve));
        return entityManager.createQuery(query).getResultList().size();
    }

    public List<SpaceMarine> getByPattern(String field, String value) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SpaceMarine> query = criteriaBuilder.createQuery(SpaceMarine.class);
        Root<SpaceMarine> from = query.from(SpaceMarine.class);
        query.select(from).where(criteriaBuilder.like(from.get(field), "%" + value + "%"));
        return entityManager.createQuery(query).getResultList();
    }

    public List<SpaceMarine> getByStarShipId(Long starShipId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SpaceMarine> query = criteriaBuilder.createQuery(SpaceMarine.class);
        Root<SpaceMarine> from = query.from(SpaceMarine.class);
        query.select(from).where(criteriaBuilder.equal(from.get("starShipId"), starShipId));
        return entityManager.createQuery(query).getResultList();
    }
}
