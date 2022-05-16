package com.company.Furniture.domain.products;

import com.company.Furniture.components.furniture.Component;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void create(Component product);
    List<Product> readAll();
    boolean update(Component component, UUID id);
    boolean delete(UUID id);
    void deleteAll();
}
