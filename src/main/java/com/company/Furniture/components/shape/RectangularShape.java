package com.company.Furniture.components.shape;

import com.company.Furniture.components.types.TypeShape;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RectangularShape implements Shaped {
    private final double width;
    private final double height;
    private final double depth;

    @Builder
    public RectangularShape(double width, double height, double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    @Override
    public TypeShape getShape() {
        return TypeShape.RECTANGULAR;
    }

    @Override
    public double getVolume() {
        return this.depth * this.height * this.width;
    }
}
