package com.company.Furniture.services;

import com.company.Furniture.components.furniture.Component;
import com.company.Furniture.components.types.TypeUnits;

import java.util.List;

public interface CalculateService {
    double getWeight(List<Component> furniture, TypeUnits units);
    double getWeight(Component furniture, TypeUnits units);
}
