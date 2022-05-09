package com.company.Furniture.repositories;

import com.company.Furniture.components.furniture.Component;
import com.company.Furniture.components.options.CalculateService;
import com.company.Furniture.components.options.CalculateServiceImpl;
import com.company.Furniture.components.types.TypeUnits;
import com.company.Furniture.entities.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ConnectingDBServiceImpl implements ConnectingDBService {

    private final ProductRepository productRepository;
    private final CalculateService calculateService;

    public ConnectingDBServiceImpl(ProductRepository entityRepository,
                                CalculateServiceImpl calculateWeightService){
        productRepository = entityRepository;
        calculateService = calculateWeightService;
    }

    @Override
    public void saveElement(Component component) {
        productRepository.save(new Product(component.getName(), (int) calculateService.getWeight(component, TypeUnits.GRAMS)));
    }

    @Override
    public void saveByParameters(String name, int weight) {
        productRepository.save(new Product(name, weight));
    }

    @Override
    public void saveAllElements(List<Component> componentsList) {
        for(Component component : componentsList){
            saveElement(component);
        }
    }

    @Override
    public void deleteAllElements() {
        productRepository.deleteAll();
    }

    @Override
    public void deleteByName(String name) {
        List<Product> productList = findAllElements();
        for(Product product : productList){
            if(Objects.equals(product.getName(), name))
                productRepository.delete(product);
        }
    }

    @Override
    public List<Product> findAllElements() {
        Iterable<Product> iterator = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        iterator.forEach(productList :: add);
        return productList;
    }
}
