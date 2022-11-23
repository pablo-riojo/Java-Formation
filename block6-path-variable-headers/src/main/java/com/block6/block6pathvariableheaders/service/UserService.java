package com.block6.block6pathvariableheaders.service;

import com.block6.block6pathvariableheaders.entity.User;

import java.util.Optional;

public interface UserService {
    Iterable<User> getAll();
    Optional<User> getById(Long id);
    void create(User newUser);
}
