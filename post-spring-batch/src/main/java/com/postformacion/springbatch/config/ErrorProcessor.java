package com.postformacion.springbatch.config;

import com.postformacion.springbatch.domain.ErrorDTO;
import com.postformacion.springbatch.domain.WeatherNotation;
import org.springframework.batch.item.ItemProcessor;

public class ErrorProcessor implements ItemProcessor<WeatherNotation, ErrorDTO> {
    @Override
    public ErrorDTO process(WeatherNotation notation) {
        if(
                notation.getTemperature() > 50 || notation.getTemperature() < -20
        ) {
            return new ErrorDTO(
                    notation.getLocation(),
                    notation.getDate(),
                    notation.getTemperature()
            );
        }

        return null;
    }
}
