package com.example.moneyTransferApp.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;


public abstract class BaseSpecification<T> implements Specification<T> {
    protected SpecSearchCriteria criteria;

    public BaseSpecification(SpecSearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    public SpecSearchCriteria getCriteria() {
        return criteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NOT_EQUALITY:
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case CONTAINS:
                return builder.isMember(criteria.getValue(), root.get(criteria.getKey()));
            case GREATER_THAN:
                return builder.greaterThan(root.get(criteria.getKey()), (Comparable) criteria.getValue());
            case LESS_THAN:
                return builder.lessThan(root.get(criteria.getKey()), (Comparable) criteria.getValue());
            case EQUALITY_OR_GREATER_THAN:
                return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), (Comparable) criteria.getValue());
            case EQUALITY_OR_LESS_THAN:
                return builder.lessThanOrEqualTo(root.get(criteria.getKey()), (Comparable) criteria.getValue());
            case LIKE:
                return builder.like(root.get(criteria.getKey()), criteria.getValue().toString());
            case STARTS_WITH:
                return builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
            case ENDS_WITH:
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
            case HAVE:
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            case IN:
                root.get(criteria.getKey()).in(criteria.getValue());
            case ALL:
                return builder.conjunction();
            case IS_NULL:
                return builder.isNull(root.get(criteria.getKey()));
            case NOT_NULL:
                return builder.isNotNull(root.get(criteria.getKey()));
            case SORT:
                query.orderBy(builder.asc(root.get(criteria.getKey())));
                return builder.conjunction();
            case SORT_DESC:
                query.orderBy(builder.desc(root.get(criteria.getKey())));
                return builder.conjunction();
            default:
                return null;
        }
    }
}
