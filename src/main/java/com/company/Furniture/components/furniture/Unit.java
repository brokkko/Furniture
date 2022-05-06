package com.company.Furniture.components.furniture;

import com.company.Furniture.components.shape.Shaped;
import com.company.Furniture.components.types.TypeColor;
import com.company.Furniture.components.types.TypeMaterial;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class Unit implements Component {
    private Shaped shape;
    private TypeColor color;
    private TypeMaterial material;

    public Unit(Shaped shape, TypeColor color, TypeMaterial material) {
        this.shape = shape;
        this.color = color;
        this.material = material;
    }

    @Override
    public double getWeight() {
        return this.shape.getVolume() * this.material.getDensity();
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
