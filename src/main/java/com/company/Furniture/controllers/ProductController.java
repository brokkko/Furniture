package com.company.Furniture.controllers;

import com.company.Furniture.components.furniture.Component;
import com.company.Furniture.domain.products.Product;
import com.company.Furniture.exceptions.DefaultAdvice;
import com.company.Furniture.exceptions.NotFoundException;
import com.company.Furniture.parsers.ParsingDataService;
import com.company.Furniture.domain.products.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        try {
            productService.create(parsingDataService.parseData(file.getInputStream()));
        } catch (NotFoundException e){
            return new DefaultAdvice().handleException(e);
        } catch (IOException e) {
            log.warn("Parse file problems: ", e);
            return new ResponseEntity<>(file, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> read(){
        return new ResponseEntity<>(productService.readAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable(name = "id") UUID id, @RequestBody Component component){
        try{
            return new ResponseEntity<>(productService.update(component, id), HttpStatus.OK);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") UUID id){
        try{
            return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
        } catch (NotFoundException e){
            return new DefaultAdvice().handleException(e);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(){
        productService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
