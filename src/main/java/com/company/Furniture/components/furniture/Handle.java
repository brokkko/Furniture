package com.company.Furniture.components.furniture;
import com.company.Furniture.components.shape.Shaped;
import com.company.Furniture.components.types.TypeColor;
import com.company.Furniture.components.types.TypeMaterial;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Handle implements Component {
    private Shaped shape;
    private TypeColor color;
    private TypeMaterial material;

    @Builder
    public Handle(Shaped shape, TypeColor color, TypeMaterial material) {
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
