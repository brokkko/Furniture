package com.company.Furniture.components.Furniture;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Table implements Component {
    private List<Component> elements;

    @Builder
    public Table(List<Component> elements){
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
}
