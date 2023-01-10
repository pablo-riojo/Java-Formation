package com.postformacion.springbatch.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter @Setter @ToString
@Entity
@Table(name = "WEATHER_RISK")
public class WeatherRisk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WEATHER_NOTATION")
    private WeatherNotation weatherNotation;

    private String date;
    private Risk risk;

    public WeatherRisk(WeatherNotation weatherNotation, String date, Risk risk) {
        this.weatherNotation = weatherNotation;
        this.date = date;
        this.risk = risk;
    }
}
