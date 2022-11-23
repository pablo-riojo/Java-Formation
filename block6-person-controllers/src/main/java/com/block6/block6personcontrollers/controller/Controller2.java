package com.block6.block6personcontrollers.controller;

import com.block6.block6personcontrollers.entity.City;
import com.block6.block6personcontrollers.entity.Person;
import com.block6.block6personcontrollers.service.CityService;
import com.block6.block6personcontrollers.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controller2")
public class Controller2 {
    @Autowired
    private PersonService personService;
    @Autowired
    private CityService cityService;

    @GetMapping("/person")
    public Iterable<Person> getPersonList() {
        return personService.getAll();
    }

    @GetMapping("/city")
    public Iterable<City> getCityList() {
        return cityService.getAll();
    }
}
