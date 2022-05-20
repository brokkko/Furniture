package com.company.Furniture.domain.products;

import com.company.Furniture.components.furniture.Component;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void create(Component product);
    Product read(UUID id);
    List<Product> readAll();
    boolean update(Component component, UUID id);
    Product delete(UUID id);
    void deleteAll();
}
