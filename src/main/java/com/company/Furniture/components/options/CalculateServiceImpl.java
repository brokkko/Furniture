package com.company.Furniture.components.options;

import com.company.Furniture.components.furniture.Component;
import com.company.Furniture.components.types.TypeUnits;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateServiceImpl implements CalculateService {
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
