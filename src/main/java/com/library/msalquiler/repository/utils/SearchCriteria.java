package com.library.msalquiler.repository.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class SearchCriteria<T> implements Specification<T> {

    private final List<SearchStatement> list = new LinkedList<>();

    public void add(SearchStatement criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new LinkedList<>();
        String key;
        Object value;
        SearchOperation operation;
        for (SearchStatement criteria : list) {
            key = criteria.getKey();
            value = criteria.getValue();
            operation = criteria.getOperation();
            try {
                if (operation.equals(SearchOperation.GREATER_THAN)) {
                    predicates.add(builder.greaterThan(root.get(key), value.toString()));
                } else if (operation.equals(SearchOperation.LESS_THAN)) {
                    predicates.add(builder.lessThan(root.get(key), value.toString()));
                } else if (operation.equals(SearchOperation.GREATER_THAN_EQUAL)) {
                    predicates.add(builder.greaterThanOrEqualTo(root.get(key), value.toString()));
                } else if (operation.equals(SearchOperation.LESS_THAN_EQUAL)) {
                    predicates.add(builder.lessThanOrEqualTo(root.get(key), value.toString()));
                } else if (operation.equals(SearchOperation.NOT_EQUAL)) {
                    predicates.add(builder.notEqual(root.get(key), value));
                } else if (operation.equals(SearchOperation.EQUAL)) {
                    predicates.add(builder.equal(root.get(key), value));
                } else if (operation.equals(SearchOperation.MATCH)) {
                    predicates.add(builder.like(builder.lower(root.get(key)), "%" + value.toString().toLowerCase() + "%"));
                } else if (operation.equals(SearchOperation.MATCH_END)) {
                    predicates.add(builder.like(builder.lower(root.get(key)), value.toString().toLowerCase() + "%"));
                }
            } catch (IllegalArgumentException e) {
                log.error("Error al convertir el valor para la clave: " + key, e);
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
