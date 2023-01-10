package com.postformacion.springbatch.listener;

import com.postformacion.springbatch.domain.WeatherNotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;


public class NotationReaderListener implements ItemReadListener<WeatherNotation> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotationReaderListener.class);

    @Override
    public void beforeRead() {}

    @Override
    public void afterRead(WeatherNotation notation) {
        LOGGER.info("afterRead: " + notation.toString());
    }

    @Override
    public void onReadError(Exception e){
        LOGGER.info("onReadError: " + e.toString());
    }
}
