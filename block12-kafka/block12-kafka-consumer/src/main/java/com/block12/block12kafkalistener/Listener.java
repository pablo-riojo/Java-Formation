package com.block12.block12kafkalistener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {
    @KafkaListener(topics = "b10", groupId = "groupdId", containerGroup = "messageFactory")
    void listener(Message data) {
        System.out.println("Message received: " + data.message() + " at " + data.created());
    }
}
