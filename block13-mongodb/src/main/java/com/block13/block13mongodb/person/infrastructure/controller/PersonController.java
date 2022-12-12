package com.block13.block13mongodb.person.infrastructure.controller;

import com.block13.block13mongodb.person.application.PersonSvc;
import com.block13.block13mongodb.person.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonSvc svc;

    @GetMapping("/all")
    List<Person> getAll() {
        return svc.findAll();
    }

    @GetMapping
    List<Person> getByPage(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
        return svc.findAllPaginated(offset, pageSize).getContent();
    }

    @GetMapping("/{id}")
    Person getById(@PathVariable int id) {
        return svc.findById(id);
    }

    @PostMapping
    Person create(@RequestBody Person newPerson) {
        return svc.save(newPerson);
    }

    @PutMapping("/{id}")
    Person putById(@RequestBody Person newPerson, @PathVariable int id) {
        return svc.update(newPerson, id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        svc.delete(id);
    }
}
