package com.block6.block6pathvariableheaders.controller;

import com.block6.block6pathvariableheaders.entity.User;
import com.block6.block6pathvariableheaders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        userService.create(newUser);

        return newUser;
    }

    @PutMapping
    public String getParams(@RequestParam(name = "var1") int var1,
                            @RequestParam(name = "var2") int var2) {
        return "var1: " + var1 + " var2: " + var2;
    }
}
