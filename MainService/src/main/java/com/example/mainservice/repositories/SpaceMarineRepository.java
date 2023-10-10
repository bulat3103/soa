package com.example.mainservice.repositories;

import com.example.mainservice.entity.*;
import com.example.mainservice.model.Filter;
import com.example.mainservice.model.Sort;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SpaceMarineRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public SpaceMarine save(SpaceMarine spaceMarine) {
        entityManager.persist(spaceMarine);
        entityManager.flush();
        return spaceMarine;
    }

    public SpaceMarine getById(Long id) {
        return entityManager.find(SpaceMarine.class, id);
    }

    public List<SpaceMarine> getAllSpaceMarines(List<Sort> sorts, List<Filter> filters, Integer page, Integer limit) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SpaceMarine> criteriaQuery = criteriaBuilder.createQuery(SpaceMarine.class);
        Root<SpaceMarine> from = criteriaQuery.from(SpaceMarine.class);
        if (!filters.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();
            for (Filter filter : filters) {
                if (filter.getOperation().equals(FilterOperation.EQ)) {
                    predicates.add(criteriaBuilder.equal(
                            from.get(filter.getField().getName()),
                            filter.getValue()
                    ));
                } else if (filter.getOperation().equals(FilterOperation.NE)) {
                    predicates.add(criteriaBuilder.notEqual(
                            from.get(filter.getField().getName()),
                            filter.getValue()
                    ));
                } else if (filter.getOperation().equals(FilterOperation.GT)) {
                    predicates.add(criteriaBuilder.greaterThan(
                            from.get(filter.getField().getName()),
                            filter.getValue()
                    ));
                } else if (filter.getOperation().equals(FilterOperation.LT)) {
                    predicates.add(criteriaBuilder.lessThan(
                            from.get(filter.getField().getName()),
                            filter.getValue()
                    ));
                } else if (filter.getOperation().equals(FilterOperation.GTE)) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                            from.get(filter.getField().getName()),
                            filter.getValue()
                    ));
                } else if (filter.getOperation().equals(FilterOperation.LTE)) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(
                            from.get(filter.getField().getName()),
                            filter.getValue()
                    ));
                }
            }
            criteriaQuery.select(from).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        }
        if (!sorts.isEmpty()) {
            List<Order> order = new ArrayList<>();
            for (Sort sort : sorts) {
                if (sort.getOperation().equals(SortOperation.ASC)) {
                    order.add(criteriaBuilder.asc(
                            from.get(sort.getField().getName())
                    ));
                } else if (sort.getOperation().equals(SortOperation.DESC)) {
                    order.add(criteriaBuilder.desc(
                            from.get(sort.getField().getName())
                    ));
                }
            }
            criteriaQuery.select(from).orderBy(order);
        }
        return entityManager.createQuery(criteriaQuery)
                .setFirstResult((page - 1) * limit)
                .setMaxResults(limit)
                .getResultList();
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
