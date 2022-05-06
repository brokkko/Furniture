package com.company.Furniture.components.furniture;

import com.company.Furniture.components.types.TypeMaterial;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class Filler implements Component {
    private double volume; // объем
    private TypeMaterial material;

    public Filler(double volume, TypeMaterial material) {
        this.volume = volume;
        this.material = material;
    }

    @Override
    public double getWeight() {
        return this.volume * this.material.getDensity();
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
