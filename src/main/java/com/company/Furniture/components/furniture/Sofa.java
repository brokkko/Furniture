package com.company.Furniture.components.furniture;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Sofa implements Component {
    private List<Component> elements;

    @Builder
    public Sofa(List<Component> elements){
        this.elements = elements;
    }

    @Override
    public double getWeight() {
        double sum = 0;
        for(Component element : this.elements){
            sum += element.getWeight();
        }
        return Math.round(sum);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
