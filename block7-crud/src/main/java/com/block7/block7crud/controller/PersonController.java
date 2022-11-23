package com.block7.block7crud.controller;

import com.block7.block7crud.application.PersonSvc;
import com.block7.block7crud.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonSvc personSvc;

    @GetMapping
    public Iterable<Person> getPersonList() {
        return personSvc.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> getPersonById(@PathVariable Long id) {
        return personSvc.findById(id);
    }

    @PostMapping
    public Person createPerson(@RequestBody Person newPerson) {
        return personSvc.create(newPerson);
    }

    @PutMapping("/{id}")
    public Optional<Person> updatePerson(@PathVariable Long id, @RequestBody Person newPerson) {
        return personSvc.update(id, newPerson);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        return personSvc.delete(id);
    }
}
