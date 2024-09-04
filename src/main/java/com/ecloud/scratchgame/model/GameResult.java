package com.ecloud.scratchgame.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GameResult {
    Matrix matrix;
    double reward;
    Map<String, List<String>> finalWinningCombination;
    String appliedBonusSymbol;

    public GameResult(Matrix matrix, double reward, Map<String, List<String>> finalWinningCombination, String appliedBonusSymbol) {
        this.matrix = matrix;
        this.reward = reward;
        this.finalWinningCombination = finalWinningCombination;
        this.appliedBonusSymbol = appliedBonusSymbol;
    }
}
