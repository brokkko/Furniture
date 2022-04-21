package com.company.Furniture.services;

import com.company.Furniture.components.Furniture.Component;
import com.company.Furniture.components.types.TypeUnits;

import java.util.List;

public class CalculateServiceImpl implements CalculateService{
    @Override
    public double getWeight(List<Component> furniture, TypeUnits units) {
        double sumWeight = 0;
        for(Component element : furniture){
            sumWeight += this.getWeight(element, units);
        }
        return sumWeight;

    }

    @Override
    public double getWeight(Component furniture, TypeUnits units) {
        return Math.round(furniture.getWeight() * units.getCoefficient());
    }
}
