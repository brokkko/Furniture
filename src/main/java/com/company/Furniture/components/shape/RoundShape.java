package com.company.Furniture.components.shape;

import com.company.Furniture.components.types.TypeShape;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class RoundShape implements Shaped {
    private double height;
    private double radius;

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
