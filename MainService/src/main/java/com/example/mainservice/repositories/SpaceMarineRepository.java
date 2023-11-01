package com.example.mainservice.repositories;

import com.example.mainservice.entity.*;
import com.example.mainservice.model.AstartesCategory;
import com.example.mainservice.model.Filter;
import com.example.mainservice.model.Sort;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SpaceMarineRepository {
    @Inject
    private ManagerProvider managerProvider;

    public SpaceMarine save(SpaceMarine spaceMarine) {
        managerProvider.getEm().persist(spaceMarine);
        managerProvider.getEm().flush();
        return spaceMarine;
    }

    public SpaceMarine getById(Long id) {
        return managerProvider.getEm().find(SpaceMarine.class, id);
    }

    public List<SpaceMarine> getAllSpaceMarines(List<Sort> sorts, List<Filter> filters, Integer page, Integer limit) {
        CriteriaBuilder criteriaBuilder = managerProvider.getEm().getCriteriaBuilder();
        CriteriaQuery<SpaceMarine> criteriaQuery = criteriaBuilder.createQuery(SpaceMarine.class);
        Root<SpaceMarine> from = criteriaQuery.from(SpaceMarine.class);
        CriteriaQuery<SpaceMarine> select = criteriaQuery.select(from);
        if (!filters.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();
            for (Filter filter : filters) {
                if (filter.getOperation().equals(FilterOperation.EQ)) {
                    if (filter.getField().getStarshipTableName() != null) {
                        predicates.add(criteriaBuilder.equal(
                                from.get("starship").get(filter.getField().getStarshipTableName()),
                                filter.getValue()
                        ));
                    } else {
                        predicates.add(criteriaBuilder.equal(
                                from.get(filter.getField().getName()),
                                filter.getValue()
                        ));
                    }
                } else if (filter.getOperation().equals(FilterOperation.NE)) {
                    if (filter.getField().getStarshipTableName() != null) {
                        predicates.add(criteriaBuilder.notEqual(
                                from.get("starship").get(filter.getField().getStarshipTableName()),
                                filter.getValue()
                        ));
                    } else {
                        predicates.add(criteriaBuilder.notEqual(
                                from.get(filter.getField().getName()),
                                filter.getValue()
                        ));
                    }
                } else if (filter.getOperation().equals(FilterOperation.GT)) {
                    if (filter.getField().getStarshipTableName() != null) {
                        predicates.add(criteriaBuilder.greaterThan(
                                from.get("starship").get(filter.getField().getStarshipTableName()),
                                filter.getValue()
                        ));
                    } else {
                        predicates.add(criteriaBuilder.greaterThan(
                                from.get(filter.getField().getName()),
                                filter.getValue()
                        ));
                    }
                } else if (filter.getOperation().equals(FilterOperation.LT)) {
                    if (filter.getField().getStarshipTableName() != null) {
                        predicates.add(criteriaBuilder.lessThan(
                                from.get("starship").get(filter.getField().getStarshipTableName()),
                                filter.getValue()
                        ));
                    } else {
                        predicates.add(criteriaBuilder.lessThan(
                                from.get(filter.getField().getName()),
                                filter.getValue()
                        ));
                    }
                } else if (filter.getOperation().equals(FilterOperation.GTE)) {
                    if (filter.getField().getStarshipTableName() != null) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                                from.get("starship").get(filter.getField().getStarshipTableName()),
                                filter.getValue()
                        ));
                    } else {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                                from.get(filter.getField().getName()),
                                filter.getValue()
                        ));
                    }
                } else if (filter.getOperation().equals(FilterOperation.LTE)) {
                    if (filter.getField().getStarshipTableName() != null) {
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(
                                from.get("starship").get(filter.getField().getStarshipTableName()),
                                filter.getValue()
                        ));
                    } else {
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(
                                from.get(filter.getField().getName()),
                                filter.getValue()
                        ));
                    }
                }
            }
            select.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        }
        if (!sorts.isEmpty()) {
            List<Order> order = new ArrayList<>();
            for (Sort sort : sorts) {
                if (sort.getOperation().equals(SortOperation.ASC)) {
                    if (sort.getField().getStarshipTableName() != null) {
                        order.add(criteriaBuilder.asc(
                                from.get("starship").get(sort.getField().getStarshipTableName())
                        ));
                    } else {
                        order.add(criteriaBuilder.asc(
                                from.get(sort.getField().getName())
                        ));
                    }
                } else if (sort.getOperation().equals(SortOperation.DESC)) {
                    if (sort.getField().getStarshipTableName() != null) {
                        order.add(criteriaBuilder.desc(
                                from.get("starship").get(sort.getField().getStarshipTableName())
                        ));
                    } else {
                        order.add(criteriaBuilder.desc(
                                from.get(sort.getField().getName())
                        ));
                    }
                }
            }
            select.orderBy(order);
        }
        return managerProvider.getEm().createQuery(select)
                .setFirstResult((page - 1) * limit)
                .setMaxResults(limit)
                .getResultList();
    }

    public void deleteById(Long id) {
        CriteriaBuilder criteriaBuilder = managerProvider.getEm().getCriteriaBuilder();
        CriteriaDelete<SpaceMarine> criteriaDelete = criteriaBuilder.createCriteriaDelete(SpaceMarine.class);
        Root<SpaceMarine> from = criteriaDelete.from(SpaceMarine.class);
        criteriaDelete.where(criteriaBuilder.equal(from.get("id"), id));
        managerProvider.getEm().createQuery(criteriaDelete).executeUpdate();
    }

    public List<Integer> getUniqueHeartCountColumn() {
        CriteriaBuilder criteriaBuilder = managerProvider.getEm().getCriteriaBuilder();
        CriteriaQuery<Integer> query = criteriaBuilder.createQuery(Integer.class);
        query.select(query.from(SpaceMarine.class).get("heartCount")).distinct(true);
        TypedQuery<Integer> tq = managerProvider.getEm().createQuery(query);
        return tq.getResultList();
    }

    public Integer getLowerAchieves(String achieve) {
        CriteriaBuilder criteriaBuilder = managerProvider.getEm().getCriteriaBuilder();
        CriteriaQuery<SpaceMarine> query = criteriaBuilder.createQuery(SpaceMarine.class);
        Root<SpaceMarine> from = query.from(SpaceMarine.class);
        query.select(from).where(criteriaBuilder.lessThan(from.get("achievements"), achieve));
        return managerProvider.getEm().createQuery(query).getResultList().size();
    }

    public List<SpaceMarine> getByPattern(String field, String value) {
        CriteriaBuilder criteriaBuilder = managerProvider.getEm().getCriteriaBuilder();
        CriteriaQuery<SpaceMarine> query = criteriaBuilder.createQuery(SpaceMarine.class);
        Root<SpaceMarine> from = query.from(SpaceMarine.class);
        query.select(from).where(criteriaBuilder.like(from.get(field), "%" + value + "%"));
        return managerProvider.getEm().createQuery(query).getResultList();
    }

    public List<SpaceMarine> getByStarShipId(Long starShipId) {
        CriteriaBuilder criteriaBuilder = managerProvider.getEm().getCriteriaBuilder();
        CriteriaQuery<SpaceMarine> query = criteriaBuilder.createQuery(SpaceMarine.class);
        Root<SpaceMarine> from = query.from(SpaceMarine.class);
        query.select(from).where(criteriaBuilder.equal(from.get("starShipId"), starShipId));
        return managerProvider.getEm().createQuery(query).getResultList();
    }
}
