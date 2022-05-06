package com.company.Furniture.components.furniture;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "name")
public interface Component {
    double getWeight();
    String getName();
}
