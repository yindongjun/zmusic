package com.example.zmusic.repository.specs;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public abstract class AbstractSpecification<T> implements Specification<T> {
  private List<SearchCriteria> searchCriteriaList = new ArrayList<>();

  public AbstractSpecification() {}

  public AbstractSpecification(List<SearchCriteria> searchCriteriaList) {
    this.searchCriteriaList = searchCriteriaList;
  }

  public AbstractSpecification<T> add(SearchCriteria searchCriteria) {
    this.searchCriteriaList.add(searchCriteria);
    return this;
  }

  @Override
  public Predicate toPredicate(
      Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    List<Predicate> predicates = new ArrayList<>();
    for (SearchCriteria searchCriteria : searchCriteriaList) {
      if (searchCriteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
        predicates.add(
            criteriaBuilder.greaterThan(
                root.get(searchCriteria.getKey()), searchCriteria.getValue().toString()));
      } else if (searchCriteria.getOperation().equals(SearchOperation.LESS_THAN)) {
        predicates.add(
            criteriaBuilder.lessThan(
                root.get(searchCriteria.getKey()), searchCriteria.getValue().toString()));
      } else if (searchCriteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
        predicates.add(
            criteriaBuilder.greaterThanOrEqualTo(
                root.get(searchCriteria.getKey()), searchCriteria.getValue().toString()));
      } else if (searchCriteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
        predicates.add(
            criteriaBuilder.lessThanOrEqualTo(
                root.get(searchCriteria.getKey()), searchCriteria.getValue().toString()));
      } else if (searchCriteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
        predicates.add(
            criteriaBuilder.notEqual(root.get(searchCriteria.getKey()), searchCriteria.getValue()));
      } else if (searchCriteria.getOperation().equals(SearchOperation.EQUAL)) {
        predicates.add(
            criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue()));
      } else if (searchCriteria.getOperation().equals(SearchOperation.MATCH)) {
        predicates.add(
            criteriaBuilder.like(
                root.get(searchCriteria.getKey()),
                "%" + searchCriteria.getValue().toString() + "%"));
      } else if (searchCriteria.getOperation().equals(SearchOperation.MATCH_START)) {
        predicates.add(
            criteriaBuilder.like(
                root.get(searchCriteria.getKey()), searchCriteria.getValue().toString() + "%"));
      } else if (searchCriteria.getOperation().equals(SearchOperation.MATCH_END)) {
        predicates.add(
            criteriaBuilder.like(
                root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue().toString()));
      } else if (searchCriteria.getOperation().equals(SearchOperation.IN)) {
        predicates.add(
            criteriaBuilder.in(root.get(searchCriteria.getKey())).value(searchCriteria.getValue()));
      } else if (searchCriteria.getOperation().equals(SearchOperation.NOT_IN)) {
        predicates.add(
            criteriaBuilder.not(root.get(searchCriteria.getKey())).in(searchCriteria.getValue()));
      }
    }
    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
  }
}
