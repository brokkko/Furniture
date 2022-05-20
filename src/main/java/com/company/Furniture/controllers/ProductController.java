package com.company.Furniture.controllers;

import com.company.Furniture.components.furniture.Component;
import com.company.Furniture.domain.products.Product;
import com.company.Furniture.parsers.ParsingDataService;
import com.company.Furniture.domain.products.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final ParsingDataService parsingDataService;

    @Autowired
    public ProductController(ProductService productService,
                             ParsingDataService parsingDataService){
        this.productService = productService;
        this.parsingDataService = parsingDataService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestParam("file") MultipartFile file){
        // error handler
        try {
            productService.create(parsingDataService.parseData(file.getInputStream()));
        } catch (NullPointerException | IOException e) {
            log.warn("Parse file problems: ", e);
            return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> read(){
        return new ResponseEntity<>(productService.readAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable(name = "id") UUID id, @RequestBody Component component){
        if(productService.update(component, id)) // id !!
            return new ResponseEntity<>(productService.read(id), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> delete(@PathVariable(name = "id") UUID id){
        Product product = productService.delete(id); // !!
        if(product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(){
        productService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
