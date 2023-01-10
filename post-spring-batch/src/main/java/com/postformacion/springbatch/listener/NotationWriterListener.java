package com.postformacion.springbatch.listener;

import com.postformacion.springbatch.domain.WeatherRisk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class NotationWriterListener implements ItemWriteListener<WeatherRisk> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotationWriterListener.class);

    @Override
    public void beforeWrite(List<? extends WeatherRisk> list) {}

    @Override
    public void afterWrite(List<? extends WeatherRisk> list) {
        for (WeatherRisk risk : list) {
            LOGGER.info("afterWrite :" + risk.toString());
        }
    }

    @Override
    public void onWriteError(Exception e, List<? extends WeatherRisk> list) {
        LOGGER.info("onWriteError: " + e.toString());
    }
}
