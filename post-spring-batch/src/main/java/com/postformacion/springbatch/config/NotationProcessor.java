package com.postformacion.springbatch.config;

import com.postformacion.springbatch.domain.Risk;
import com.postformacion.springbatch.domain.WeatherNotation;
import com.postformacion.springbatch.domain.WeatherRisk;
import org.springframework.batch.item.ItemProcessor;

public class NotationProcessor implements ItemProcessor<WeatherNotation, WeatherRisk> {
    @Override
    public WeatherRisk process(WeatherNotation notation) {
        String date = notation.getDate();
        int temperature = notation.getTemperature();
        Risk risk;

        if(temperature >= 36) {
            risk = Risk.HIGH;
        } else if(temperature > -20) {
            risk = Risk.MID;
        } else {
            risk = Risk.LOW;
        }

        if(temperature > 50 || temperature < -20) {
            return null;
        }

        return new WeatherRisk(notation, date, risk);
    }
}
