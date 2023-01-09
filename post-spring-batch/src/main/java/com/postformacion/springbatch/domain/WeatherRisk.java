package com.postformacion.springbatch.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "WEATHER_RISK")
public class WeatherRisk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "WEATHER_NOTATION")
    private WeatherNotation weatherNotation;

    @Column(nullable = false)
    private final Date date = new Date();

    @Column(nullable = false)
    private Risk risk;
}
