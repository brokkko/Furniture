package com.company.Furniture.repositories;

import com.company.Furniture.entities.Furniture;
import org.springframework.data.repository.CrudRepository;

public interface FurnitureRepository extends CrudRepository<Furniture, Integer> {

}

