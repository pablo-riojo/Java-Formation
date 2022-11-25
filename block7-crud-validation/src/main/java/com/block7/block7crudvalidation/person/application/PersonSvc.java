package com.block7.block7crudvalidation.person.application;

import com.block7.block7crudvalidation.person.domain.Person;

import java.util.List;

public interface PersonSvc {
    List<Person> findAll();
    Person findById(Long id);
    Person findByUser(String user);
    void update(Person newPerson, Long id);
    void delete(Long id);
    void save(Person person);
}
