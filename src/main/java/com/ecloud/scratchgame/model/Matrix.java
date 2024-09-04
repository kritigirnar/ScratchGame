package com.ecloud.scratchgame.model;

import lombok.Data;

@Data
public class Matrix {

    private String[][] matrix;

    public Matrix(String[][] matrix) {
        this.matrix = matrix;
    }

}
