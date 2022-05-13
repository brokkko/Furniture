package com.company.Furniture.domain.products;

import com.company.Furniture.components.furniture.Component;
import com.company.Furniture.components.options.CalculateService;
import com.company.Furniture.components.options.CalculateServiceImpl;
import com.company.Furniture.components.types.TypeUnits;
import com.company.Furniture.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CalculateService calculateService;

    public ProductServiceImpl(ProductRepository entityRepository,
                              CalculateServiceImpl calculateWeightService){
        productRepository = entityRepository;
        calculateService = calculateWeightService;
    }

    @Override
    public void save(Component component) {
        productRepository.save(new Product(component.getName(), (int) calculateService.getWeight(component, TypeUnits.GRAMS)));
    }

    @Override
    public void saveAll(List<Component> componentsList) {
        for(Component component : componentsList){
            save(component);
        }
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public void deleteAll(List<Component> productList) {
        for(Component component : productList){
            productRepository.delete(
                    new Product(component.getName(), (int) calculateService.getWeight(component, TypeUnits.GRAMS))
            );
        }
    }

    @Override
    public void deleteByName(String name) {
        List<Product> productList = findAll();
        for(Product product : productList){
            if(Objects.equals(product.getName(), name))
                productRepository.delete(product);
        }
    }

    @Override
    public List<Product> findAll() {
        Iterable<Product> iterator = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        iterator.forEach(productList :: add);
        return productList;
    }
}
