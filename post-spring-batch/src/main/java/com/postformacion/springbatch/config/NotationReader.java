package com.postformacion.springbatch.config;

import com.postformacion.springbatch.domain.WeatherNotation;
import org.springframework.batch.item.ItemReader;

public class NotationReader implements ItemReader<WeatherNotation> {
    @Override
    public WeatherNotation read() {
        return new WeatherNotation();
    }
}
