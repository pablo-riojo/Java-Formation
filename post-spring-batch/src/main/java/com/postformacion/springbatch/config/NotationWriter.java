package com.postformacion.springbatch.config;

import com.postformacion.springbatch.domain.WeatherRisk;
import com.postformacion.springbatch.repository.WeatherRiskRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NotationWriter implements ItemWriter<WeatherRisk> {
    @Autowired
    private WeatherRiskRepository repository;

    @Override
    public void write(List<? extends WeatherRisk> risks) {
        if (!risks.isEmpty())
            repository.saveAll(risks);
    }
}
