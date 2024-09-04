package com.ecloud.scratchgame.model;

import lombok.Data;

import java.util.Map;

@Data
public class Config {
    private int columns;
    private int rows;
    private Map<String, Symbol> symbols;
    private Map<String, Win_Combination> win_combinations;
}
