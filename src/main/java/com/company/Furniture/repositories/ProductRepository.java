package com.company.Furniture.repositories;

import com.company.Furniture.domain.products.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}

