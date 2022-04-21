package com.company.Furniture.repository;

import com.company.Furniture.entity.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface EntityRepository extends JpaRepository<Entity, Integer> {

}
