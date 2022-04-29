package com.company.Furniture.services;

import com.company.Furniture.entities.Furniture;
import com.company.Furniture.repositories.FurnitureRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataLoaderServiceImpl implements DataLoaderService {

    private final FurnitureRepository furnitureEntityRepository;

    public DataLoaderServiceImpl(FurnitureRepository entityRepository){
        this.furnitureEntityRepository = entityRepository;
    }

    public void saveAll(List<Furniture> furnitureEntityList){
        furnitureEntityRepository.saveAll(furnitureEntityList);
    }

    public void deleteAll(){
        furnitureEntityRepository.deleteAll();
    }

    public List<Furniture> findAll(){
        Iterable<Furniture> iterator = furnitureEntityRepository.findAll();
        List<Furniture> furnitureList = new ArrayList<>();
        iterator.forEach(furnitureList :: add);
        return furnitureList;
    }

}
