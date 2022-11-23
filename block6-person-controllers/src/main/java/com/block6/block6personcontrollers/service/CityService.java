package com.block6.block6personcontrollers.service;

import com.block6.block6personcontrollers.entity.City;

public interface CityService {
    Iterable<City> getAll();
    void create(City newPerson);
}