package com.ecloud.scratchgame.controller;

import com.ecloud.scratchgame.model.Matrix;
import com.ecloud.scratchgame.model.GameResult;
import com.ecloud.scratchgame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/play/{betAmount}")
    public GameResult playGame(@PathVariable int betAmount) {
        Matrix matrix = gameService.generateMatrix();
        return gameService.evaluateMatrix(matrix, betAmount);
    }
}
