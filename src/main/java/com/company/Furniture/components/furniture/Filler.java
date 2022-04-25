package com.company.Furniture.components.furniture;

import com.company.Furniture.components.types.TypeMaterial;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Filler implements Component {
    private double volume; // объем
    private TypeMaterial material;

    @Builder
    public Filler(double volume, TypeMaterial material) {
        this.volume = volume;
        this.material = material;
    }

    @Override
    public double getWeight() {
        return this.volume * this.material.getDensity();
    }
}
