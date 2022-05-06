package com.company.Furniture.components.shape;

import com.company.Furniture.components.types.TypeShape;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class RectangularShape implements Shaped {
    private double width;
    private double height;
    private double depth;

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
