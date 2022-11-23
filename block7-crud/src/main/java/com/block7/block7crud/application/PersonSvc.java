package com.block7.block7crud.application;

import com.block7.block7crud.domain.Person;

import java.util.Optional;

public interface PersonSvc {
        Iterable<Person> findAll();
        Optional<Person> findById(Long id);
        Person create(Person newPerson);

        Optional<Person> update(Long id, Person newPerson);

        String delete(Long id);
    }
