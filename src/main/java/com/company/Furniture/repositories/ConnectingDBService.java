package com.company.Furniture.repositories;

import com.company.Furniture.components.furniture.Component;
import com.company.Furniture.entities.Product;

import java.util.List;

public interface ConnectingDBService {
    void saveElement(Component product);
    void saveByParameters(String name, int weight);
    void saveAllElements(List<Component> productList);
    void deleteAllElements();
    void deleteByName(String name);
    List<Product> findAllElements();
}
