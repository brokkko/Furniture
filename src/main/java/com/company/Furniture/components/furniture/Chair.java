package com.company.Furniture.components.furniture;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
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
    public String getName() {
        return this.getClass().getSimpleName();
    }

}
