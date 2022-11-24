package com.block7.block7crudvalidation.person.infrastructure.controller;

import com.block7.block7crudvalidation.person.application.PersonSvc;
import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonInputDTO;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonMapper;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonSvc personSvc;
    @Autowired
    PersonMapper mapper;

    @GetMapping("/all")
    public List<PersonOutputDTO> getAllPerson() {
        return personSvc.findAll().stream().map(
               p -> mapper.Instance.personToPersonOutputDTO(p)
        ).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDTO> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personSvc.findById(id);
        if (person.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found");

        return ResponseEntity.ok(mapper.Instance.personToPersonOutputDTO(person.get()));
    }

    @GetMapping("/user/{user}")
    public ResponseEntity<PersonOutputDTO> getPersonByUser(@PathVariable String user) {
        Optional<Person> person = personSvc.findByUser(user);
        if (person.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with username " + user + " not found");

        return ResponseEntity.ok(mapper.Instance.personToPersonOutputDTO(person.get()));
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody PersonInputDTO person) {
        Person personMapped = mapper.Instance.personInputDTOToPerson(person);

        return ResponseEntity.status(HttpStatus.CREATED).body(personSvc.save(personMapped));
    }

    @PutMapping("{id}")
    public ResponseEntity<PersonOutputDTO> updatePerson(@PathVariable Long id) {
        Optional<Person> person = personSvc.findById(id);
        if (person.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found");

        return ResponseEntity.ok(mapper.Instance.personToPersonOutputDTO(person.get()));
    }
}
