package com.justeattakeaway.codechallenge.kafka.consumer;

import com.justeattakeaway.codechallenge.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WinnerConsumer {

    private PlayerService playerService;

    @KafkaListener(topics = "${kafka.topic.winner.current}", groupId = "game-group")
    public void gameResult(String message) {
        playerService.printWinner(message);
    }
}
