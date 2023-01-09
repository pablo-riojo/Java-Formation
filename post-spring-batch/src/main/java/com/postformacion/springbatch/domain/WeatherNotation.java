package com.postformacion.springbatch.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@Entity
@Table(name = "WEATHER_NOTATIONS")
public class WeatherNotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "WEATHER_RISK")
    private WeatherRisk weatherRisk;

    @Column(nullable = false)
    private final Date date = new Date();

    @Column(nullable = false)
    private double temperature;
}
