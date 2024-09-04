package com.ecloud.scratchgame.model;

import lombok.Data;

@Data
public class Win_Combination {
    private String name;
    private double reward_multiplier;
    private String when;
    private int count;
    private String group;
    private String[][] covered_areas;
}
