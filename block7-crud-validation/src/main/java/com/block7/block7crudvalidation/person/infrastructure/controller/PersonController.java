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

import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonSvc personSvc;

    @GetMapping("/all")
    public List<PersonOutputDTO> getAllPerson() {
        return personSvc.findAll().stream().map(
               p -> PersonMapper.Instance.personToPersonOutputDTO(p)
        ).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDTO> getPersonById(@PathVariable Long id) {
        Person person = personSvc.findById(id);

        PersonOutputDTO response = PersonMapper.Instance.personToPersonOutputDTO(person);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{user}")
    public ResponseEntity<PersonOutputDTO> getPersonByUser(@PathVariable String user) {
        Person person = personSvc.findByUser(user);

        PersonOutputDTO response = PersonMapper.Instance.personToPersonOutputDTO(person);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PersonOutputDTO> createPerson(@RequestBody PersonInputDTO personInput) {
        Person newPerson = PersonMapper.Instance.personInputDTOToPerson(personInput);
        personSvc.save(newPerson);

        PersonOutputDTO response = PersonMapper.Instance.personToPersonOutputDTO(newPerson);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonOutputDTO> updatePerson(@PathVariable Long id, @RequestBody PersonInputDTO personInput) {
        Person person = personSvc.findById(id);

        Person newPerson = PersonMapper.Instance.personInputDTOToPerson(personInput);
        personSvc.update(newPerson, id);

        PersonOutputDTO response = PersonMapper.Instance.personToPersonOutputDTO(newPerson);
        response.setUpdatedAt(new Date());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersonById(@PathVariable Long id) {
        Person person = personSvc.findById(id);

        personSvc.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
