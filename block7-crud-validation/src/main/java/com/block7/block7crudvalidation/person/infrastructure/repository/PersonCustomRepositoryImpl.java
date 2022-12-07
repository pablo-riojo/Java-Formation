package com.block7.block7crudvalidation.person.infrastructure.repository;

import com.block7.block7crudvalidation.person.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
public class PersonCustomRepositoryImpl implements PersonCustomRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Optional<List<Person>> findByName(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery();

        Root<Person> root = query.from(Person.class);
        Predicate namePredicate = builder.equal(root.get("name"), name);

        query.where(namePredicate);
        query.orderBy(builder.asc(root.get("user")));
        query.select(root);

        TypedQuery<Person> finalQuery = entityManager.createQuery(query);

        return Optional.ofNullable(finalQuery.getResultList());
    }

    @Override
    public Optional<List<Person>> findBySurname(String surname) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery();

        Root<Person> root = query.from(Person.class);
        Predicate surnamePredicate = builder.equal(root.get("surname"), surname);

        query.where(surnamePredicate);
        query.orderBy(builder.asc(root.get("user")));
        query.select(root);

        TypedQuery<Person> finalQuery = entityManager.createQuery(query);

        return Optional.ofNullable(finalQuery.getResultList());
    }

    @Override
    public Optional<List<Person>> findByGreaterCreation(Date createdAt) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery();

        Root<Person> root = query.from(Person.class);
        Predicate createdAtPredicate = builder.greaterThan(root.get("createdAt"), createdAt);

        query.where(createdAtPredicate);
        query.orderBy(builder.asc(root.get("user")));
        query.select(root);

        TypedQuery<Person> finalQuery = entityManager.createQuery(query);

        return Optional.ofNullable(finalQuery.getResultList());
    }

    @Override
    public Optional<List<Person>> findByLowerCreation(Date createdAt) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery();

        Root<Person> root = query.from(Person.class);

        Predicate createdAtPredicate = builder.lessThan(root.get("createdAt"), createdAt);

        query.where(createdAtPredicate);
        query.orderBy(builder.asc(root.get("user")));
        query.select(root);

        TypedQuery<Person> finalQuery = entityManager.createQuery(query);

        return Optional.ofNullable(finalQuery.getResultList());    }
}
