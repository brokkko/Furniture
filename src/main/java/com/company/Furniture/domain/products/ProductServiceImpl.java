package com.company.Furniture.domain.products;

import com.company.Furniture.components.furniture.Component;
import com.company.Furniture.components.options.CalculateService;
import com.company.Furniture.components.options.CalculateServiceImpl;
import com.company.Furniture.components.types.TypeUnits;
import com.company.Furniture.exceptions.NotFoundException;
import com.company.Furniture.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public void create(Component component) {
        productRepository.save(new Product(component.getName(), (int) calculateService.getWeight(component, TypeUnits.GRAMS)));
    }

    @Override
    public Product read(UUID id) {
        List<Product> productList = readAll();
        for(Product product : productList){
            if(product.getId().equals(id)){
                return product;
            }
        }
        throw new NotFoundException("Object " + id + " not found.");
    }

    @Override
    public List<Product> readAll() {
        Iterable<Product> iterator = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        iterator.forEach(productList :: add);
        return productList;
    }

    @Override
    public Product update(Component component, UUID id) {
        // how to convert UUID type to int or if im gonna write a custom method - it is still for
        List<Product> productList = readAll();
        for(Product product : productList){ // !!!
            if(product.getId().equals(id)){
                Product newProduct = new Product(
                        component.getName(),
                        (int) calculateService.getWeight(component, TypeUnits.GRAMS)
                );
                newProduct.setId(id);
                productRepository.save(newProduct);
                return newProduct;
            }
        }
        throw new NotFoundException("Object " + id + " doesn't exist.");
    }

    @Override
    public Product delete(UUID id) {
        List<Product> productList = readAll();
        for(Product product : productList){
            if(product.getId().equals(id)){
                productRepository.delete(product);
                return product;
            }
        }
        throw new NotFoundException("Object " + id + " not found.");
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }
}
