package com.company.Furniture.domain.products;

import com.company.Furniture.components.furniture.Component;
import com.company.Furniture.components.options.CalculateService;
import com.company.Furniture.components.options.CalculateServiceImpl;
import com.company.Furniture.components.types.TypeUnits;
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
        return null;
    }

    @Override
    public List<Product> readAll() {
        Iterable<Product> iterator = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        iterator.forEach(productList :: add);
        return productList;
    }

    @Override
    public boolean update(Component component, UUID id) {
        List<Product> productList = readAll();
        for(Product product : productList){ // !!!
            if(product.getId().equals(id)){
                Product newProduct = new Product(
                        component.getName(),
                        (int) calculateService.getWeight(component, TypeUnits.GRAMS)
                );
                newProduct.setId(id);
                productRepository.save(newProduct);
                return true;
            }
        }
        return false;
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
        return null;
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }
}
