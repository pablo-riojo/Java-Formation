package com.block13.block13mongodb.person.application;

import com.block13.block13mongodb.person.domain.Person;
import com.block13.block13mongodb.person.infrastructure.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;


@SuppressWarnings("ALL")
@Service
public class PersonSvcImpl implements PersonSvc {
    @Autowired
    PersonRepository repository;

    @PostConstruct
    public void init() {
        repository.deleteAll();
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Person> findAllPaginated(int offset, int pageSize) {
        return repository.findAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public Person findById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person with ID " + id + " not found"));
    }

    @Override
    public Person update(Person newPerson, int id) {
        Person person = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person with ID " + id + " not found"));

        Boolean isEqual = Objects.equals(person, newPerson);

        if (isEqual) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot update. They are the same persons.");

        return repository.save(newPerson);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Person save(Person person) {
        repository.deleteAll();

        return repository.save(person);
    }
}
