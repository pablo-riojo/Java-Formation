package com.block12.block12kafkaproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("publish")
public class ProducerController {
    @Autowired
    KafkaTemplate<String, Message> template;

    @PostMapping
    public void publish(@RequestBody String content) {
        template.send("b10", new Message(content, LocalDateTime.now()));
    }
}
