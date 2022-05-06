package com.company.Furniture.components.furniture;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Frame implements Component{
    private List<Unit> bases;

    @Builder
    public Frame(List<Unit> bases) {
        this.bases = bases;
    }

    @Override
    public double getWeight() {
        double sum = 0;
        for(Component element : this.bases){
            sum += element.getWeight();
        }
        return sum;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
