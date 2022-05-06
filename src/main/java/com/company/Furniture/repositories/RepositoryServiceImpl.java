package com.company.Furniture.repositories;

import com.company.Furniture.entities.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    private final ProductRepository productRepository;

    public RepositoryServiceImpl(ProductRepository entityRepository){
        productRepository = entityRepository;
    }

    @Override
    public void saveElement(Product product) {
        productRepository.save(product);
    }

    @Override
    public void saveAllElements(List<Product> productList) {
        productRepository.saveAll(productList);
    }

    @Override
    public void deleteAllElements() {
        productRepository.deleteAll();
    }

    @Override
    public void deleteByName(List<Product> productList) {
        productRepository.deleteAll(productList);
    }

    @Override
    public List<Product> findAllElements() {
        Iterable<Product> iterator = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        iterator.forEach(productList :: add);
        return productList;
    }
}
