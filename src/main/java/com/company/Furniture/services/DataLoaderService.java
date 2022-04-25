package com.company.Furniture.services;

import com.company.Furniture.domain.FurnitureEntity;
import com.company.Furniture.repository.FurnitureEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataLoaderService {

    private final FurnitureEntityRepository furnitureEntityRepository;

    public DataLoaderService(FurnitureEntityRepository entityRepository){
        this.furnitureEntityRepository = entityRepository;
    }

    public void save(FurnitureEntity entity){
        furnitureEntityRepository.save(entity);
    }

    public void save(List<FurnitureEntity> entities){
        for(FurnitureEntity entity : entities){
            this.save(entity);
        }
    }

    public void deleteAll(){
        furnitureEntityRepository.deleteAll();
    }

    public Iterable<FurnitureEntity> list(){
        return furnitureEntityRepository.findAll();
    }

}
