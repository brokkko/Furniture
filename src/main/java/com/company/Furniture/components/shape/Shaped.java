package com.company.Furniture.components.shape;

import com.company.Furniture.components.types.TypeShape;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "name")
public interface Shaped {
    TypeShape getShape();
    double getVolume();

}
