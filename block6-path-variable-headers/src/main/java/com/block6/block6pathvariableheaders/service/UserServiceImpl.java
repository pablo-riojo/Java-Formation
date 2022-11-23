package com.block6.block6pathvariableheaders.service;


import com.block6.block6pathvariableheaders.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    List<User> userList = new ArrayList<>();

    @Override
    public Iterable<User> getAll() {
        return userList;
    }

    @Override
    public Optional<User> getById(Long id) {
        return userList.stream().filter(u -> u.getID() == id).findFirst();
    }

    @Override
    public void create(User newUser) {
        userList.add(newUser);
    }
}
