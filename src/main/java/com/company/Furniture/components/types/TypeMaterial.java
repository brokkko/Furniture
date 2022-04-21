package com.company.Furniture.components.types;

import lombok.Getter;

@Getter
public enum TypeMaterial {
    WOODEN (500.0/1000),
    GLASS (2500.0/1000),
    STONE (1800.0/1000),
    FELT (50.0/1000),
    WOOL (60.0/1000);

    private final double density;
    TypeMaterial(double density) {
        this.density = density;
    }

}
