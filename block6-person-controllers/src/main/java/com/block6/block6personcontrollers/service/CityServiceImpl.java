package com.block6.block6personcontrollers.service;

import com.block6.block6personcontrollers.entity.City;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService{
    List<City> cityList = createCityList();

    @Bean
    public List<City> createCityList() {
        return new ArrayList<>();
    }


    @Override
    public Iterable<City> getAll() {
        return cityList;
    }

    @Override
    public void create(City newCity) {
        cityList.add(newCity);
    }
}


