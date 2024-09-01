package com.justeattakeaway.codechallenge.kafka.consumer;

import com.justeattakeaway.codechallenge.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlayerConsumer {

    private PlayerService playerService;

    @KafkaListener(topics = "${kafka.topic.listener}", groupId = "game-group")
    public void listenFromPlayer1(String message) {
        playerService.processNumber(message);
    }
}
