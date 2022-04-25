package com.company.Furniture.components.furniture;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Chair implements Component {
    private List<Component> elements;


    public Chair(List<Component> elements){
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
    public String toString() {
        return "Chair{" +
                "elements=" + elements +
                '}';
    }
}
