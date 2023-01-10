package com.postformacion.springbatch.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "WEATHER_NOTATIONS")
public class WeatherNotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String location;
    private String date;
    private int temperature;
}
