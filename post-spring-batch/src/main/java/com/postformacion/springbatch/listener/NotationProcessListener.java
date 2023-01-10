package com.postformacion.springbatch.listener;

import com.postformacion.springbatch.domain.WeatherNotation;
import com.postformacion.springbatch.domain.WeatherRisk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;

public class NotationProcessListener implements ItemProcessListener<WeatherNotation, WeatherRisk> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotationProcessListener.class);

    @Override
    public void beforeProcess(WeatherNotation notation) {}

    @Override
    public void afterProcess(WeatherNotation notation, WeatherRisk risk) {
        LOGGER.info("afterProcess: " + notation + " ---> " + risk);
    }

    @Override
    public void onProcessError(WeatherNotation notation, Exception e) {
        LOGGER.info("onProcessError: " + notation.toString() + " " +  e.toString());
    }
}
