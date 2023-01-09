package com.postformacion.springbatch.repository;

import com.postformacion.springbatch.domain.WeatherNotation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherNotationRepository extends JpaRepository<WeatherNotation, Integer> {
}
