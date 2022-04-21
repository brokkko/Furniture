package com.company.Furniture.components.shape;

import com.company.Furniture.components.types.TypeShape;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RoundShape implements Shaped {
    private final double height;
    private final double radius;

    @Builder
    public RoundShape(double height, double radius) {
        this.height = height;
        this.radius = radius;
    }

    @Override
    public TypeShape getShape() {
        return TypeShape.ROUND;
    }

    @Override
    public double getVolume() {
        return this.height * this.radius * this.radius * Math.PI;
    }
}
