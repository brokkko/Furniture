package com.company.Furniture.services;

import com.company.Furniture.components.furniture.Component;
import com.company.Furniture.components.types.TypeUnits;
import com.company.Furniture.entities.Furniture;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ControllerServiceImpl implements ControllerService{
   // @Autowired
    private final DataLoaderService dataLoaderService;
    private final CalculateService calculateService;
    private final DataParserService dataParserService;

    public ControllerServiceImpl(DataLoaderService dataLoaderService) {
        this.dataLoaderService = dataLoaderService;
        this.calculateService = new CalculateServiceImpl();
        this.dataParserService = new DataParserJSON();
    }

    private Component parseFile(String file){
        return this.dataParserService.unpackingOrder(file);
    }

    @Override
    public void saveOrder(List<String> files) {
        this.dataLoaderService.deleteAll();

        List<Component> componentList = new ArrayList<>();
        for(String file: files){
            componentList.add(this.parseFile(file));
        }

        List<Furniture> furnitureEntityList = new ArrayList<>();
        for(Component component: componentList){
            furnitureEntityList.add(new Furniture(component.getName(),
                                                  (int) this.calculateService.getWeight(component, TypeUnits.GRAMS)));
        }

        this.dataLoaderService.saveAll(furnitureEntityList);
    }

    @Override
    public int findOrderWeightByName(String name) {
        return 0;
    }
}
