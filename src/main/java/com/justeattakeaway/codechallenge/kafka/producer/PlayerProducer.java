package com.justeattakeaway.codechallenge.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.producer}")
    private String topic;

    public void send(String message) {
        kafkaTemplate.send(topic, message);
    }
}
