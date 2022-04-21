package com.company.Furniture.components.types;

import lombok.Getter;

@Getter
public enum TypeUnits {
    KILOGRAMS (0.001),
    POUNDS(0.0022),
    GRAMS (1);

    private final double coefficient; // коэффициент перевода для граммов
    TypeUnits(double coefficient) {
        this.coefficient = coefficient;
    }
}
