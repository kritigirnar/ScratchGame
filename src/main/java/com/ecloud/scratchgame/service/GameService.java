package com.ecloud.scratchgame.service;

import com.ecloud.scratchgame.model.Config;
import com.ecloud.scratchgame.model.GameResult;
import com.ecloud.scratchgame.model.Matrix;
import com.ecloud.scratchgame.model.Win_Combination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {

    @Autowired
    private ConfigService configService;

    public Matrix generateMatrix() {
        Config config = configService.getConfig();
        int rows = config.getRows();
        int columns = config.getColumns();
        String[][] matrix = new String[rows][columns];
        Random rand = new Random();
        Object[] symbols = config.getSymbols().keySet().toArray();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = (String) symbols[rand.nextInt(symbols.length)];
            }
        }
        return new Matrix(matrix);
    }

    public GameResult evaluateMatrix(Matrix matrix, int betAmount) {
        Config config = configService.getConfig();
        Map<String, Win_Combination> winningCombinations = config.getWin_combinations();
        String[][] matrixArray = matrix.getMatrix();
        Map<String, List<String>> appliedWinningCombinations = new HashMap<>();
        double reward = 0;

        for (Win_Combination wc : winningCombinations.values()) {
            if (wc != null && wc.getCovered_areas() != null) {
                for (String[] area : wc.getCovered_areas()) {
                    if (isWinningArea(matrixArray, area)) {
                        reward += wc.getReward_multiplier() * betAmount;
                        appliedWinningCombinations.put(wc.getName(), Arrays.asList(area));
                    }
                }
            } else {
                if (wc == null) {
                    System.out.println("Null WinningCombination encountered.");
                } else {
                    System.out.println("Null CoveredAreas for WinningCombination: " + wc.getName());
                }
            }
        }

        return new GameResult(matrix, reward, appliedWinningCombinations, null);
    }

    private boolean isWinningArea(String[][] matrixArray, String[] area) {
        int numRows = matrixArray.length;
        int numCols = matrixArray[0].length;
        int areaLength = area.length;

        for (int i = 0; i < numRows; i++) {
            if (areaLength <= numCols) {
                for (int j = 0; j <= numCols - areaLength; j++) {
                    boolean match = true;
                    for (int k = 0; k < areaLength; k++) {
                        if (!matrixArray[i][j + k].equals(area[k])) {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
