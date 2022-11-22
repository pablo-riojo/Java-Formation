package com.block6.block6simplecontrollers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class Controller {
    @GetMapping("/{name}")
    public String greeting (@PathVariable String name) {
        return "Hola " + name;
    }

    @PostMapping("/add")
    public Person createPerson(@RequestBody Person person) {
        person.setAge((person.getAge()) + 1);

        return person;
    }
}
