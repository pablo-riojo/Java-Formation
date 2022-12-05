package com.block12.block12kafkalistener.config;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Config {
    @Value("${spring.kafka.bootstrap-servers")
    private String boostrapServers;

    public Map<String, Object> consumerConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServers);

        return config;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        JsonDeserializer<String> jsonDeserializer = new JsonDeserializer<>();
        jsonDeserializer.addTrustedPackages("org.javadev");

        return new DefaultKafkaConsumerFactory<>(
                consumerConfig(),
                new StringDeserializer(),
                new JsonDeserializer<>()
        );
    }

    @Bean
    public RecordMessageConverter converter() {
        return new JsonMessageConverter();
    }
}
