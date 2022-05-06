package com.company.Furniture.repositories;

import com.company.Furniture.entities.Product;

import java.util.List;

public interface RepositoryService {
    void saveElement(Product product);
    void saveAllElements(List<Product> productList);
    void deleteAllElements();
    void deleteByName(List<Product> productList);
    List<Product> findAllElements();
}
