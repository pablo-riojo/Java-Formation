package com.block7.block7crud.application;

import com.block7.block7crud.domain.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonSvcImpl  implements PersonSvc {
    List<Person> personList = new ArrayList<>();

    @Override
    public Iterable<Person> findAll() {
        return personList;
    }

    @Override
    public Optional<Person> findById(Long id) {
        return personList.stream().filter(person -> Objects.equals(person.getId(), id)).findFirst();
    }

    @Override
    public Person create(Person newPerson) {
        personList.add(newPerson);

        return newPerson;
    }

    @Override
    public Optional<Person> update(Long id, Person newPerson) {
        Optional<Person> person = personList.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst();
        if (person.isEmpty()) return Optional.empty();

        int personIndex = personList.indexOf(person.get());
        personList.set(personIndex, newPerson);

        return Optional.ofNullable(newPerson);
    }

    @Override
    public String delete(Long id) {
        Optional<Person> person = personList.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst();
        if (person.isEmpty()) return "Person not found";

        personList.removeIf(p -> Objects.equals(p.getId(), id));

        return "Deleted person with ID: " + id;
    }
}
