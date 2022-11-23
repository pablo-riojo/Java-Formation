package com.block6.block6personcontrollers.service;

import com.block6.block6personcontrollers.entity.Person;

public interface PersonService {
    Iterable<Person> getAll();
    void create(Person newPerson);
}
