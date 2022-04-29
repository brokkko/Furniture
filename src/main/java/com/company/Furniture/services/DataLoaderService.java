package com.company.Furniture.services;

import com.company.Furniture.entities.Furniture;

import java.util.List;

public interface DataLoaderService {
    void saveAll(List<Furniture> furnitureEntityList);
    void deleteAll();
    List<Furniture> findAll();
}
