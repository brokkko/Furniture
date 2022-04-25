package com.company.Furniture.services;

import com.company.Furniture.entities.Furniture;
import com.company.Furniture.repositories.FurnitureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataLoaderService {

    private final FurnitureRepository furnitureEntityRepository;

    public DataLoaderService(FurnitureRepository entityRepository){
        this.furnitureEntityRepository = entityRepository;
    }

    public void save(Furniture entity){
        furnitureEntityRepository.save(entity);
    }

    public void save(List<Furniture> furnitureEntityList){
        furnitureEntityRepository.saveAll(furnitureEntityList);
    }

    public void deleteAll(){
        furnitureEntityRepository.deleteAll();
    }

    public Iterable<Furniture> list(){
        return furnitureEntityRepository.findAll();
    } // List

}
