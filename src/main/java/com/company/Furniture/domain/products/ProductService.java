package com.company.Furniture.domain.products;

import com.company.Furniture.components.furniture.Component;

import java.util.List;

public interface ProductService {
    void save(Component product);
    void saveAll(List<Component> productList);
    void deleteAll();
    void deleteAll(List<Component> productList);
    void deleteByName(String name);
    List<Product> findAll();
}
