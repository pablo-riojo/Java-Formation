package com.block7.block7crudvalidation.person.application;

import com.block7.block7crudvalidation.person.domain.Person;

import java.util.List;
import java.util.Optional;

public interface PersonSvc {
    List<Person> findAll();
    Optional<Person> findById(Long id);
    Optional<Person> findByUser(String user);
    Person update(Person newPerson, Long id);
    void delete(Long id);
    Person save(Person person);
}
