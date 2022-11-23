package com.block6.block6personcontrollers.controller;

import com.block6.block6personcontrollers.entity.City;
import com.block6.block6personcontrollers.entity.Person;
import com.block6.block6personcontrollers.service.CityService;
import com.block6.block6personcontrollers.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controller1")
public class Controller1 {
    @Autowired
    private PersonService personService;
    @Autowired
    private CityService cityService;

    @PostMapping("/person")
    public Person createPerson(@RequestBody Person newPerson) {
        personService.create(newPerson);

        return newPerson;
    }

    @PostMapping("/city")
    public City createCity(@RequestBody City newCity) {
        cityService.create(newCity);

        return newCity;
    }
}
