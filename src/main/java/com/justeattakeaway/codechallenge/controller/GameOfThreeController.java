package com.justeattakeaway.codechallenge.controller;

import com.justeattakeaway.codechallenge.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("game")
public class GameOfThreeController {

    private PlayerService playerService;

    @PostMapping("/start")
    public void startGame() {
        playerService.startGame();
    }
}


