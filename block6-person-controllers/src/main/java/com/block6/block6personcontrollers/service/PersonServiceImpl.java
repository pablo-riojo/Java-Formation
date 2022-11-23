package com.block6.block6personcontrollers.service;

import com.block6.block6personcontrollers.entity.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{
    List<Person> personList = new ArrayList<>();

    @Override
    public Iterable<Person> getAll() {
        return personList;
    }

    @Override
    public void create(Person newPerson) {
        personList.add(newPerson);
    }
}
