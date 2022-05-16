package com.company.Furniture.controllers;

import com.company.Furniture.components.furniture.Component;
import com.company.Furniture.domain.products.Product;
import com.company.Furniture.parsers.ParsingDataService;
import com.company.Furniture.domain.products.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import org.slf4j.Logger;

@RestController
@Slf4j
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private final ParsingDataService parsingDataService;

    @Autowired
    public ProductController(ProductService productService,
                             ParsingDataService parsingDataService){
        this.productService = productService;
        this.parsingDataService = parsingDataService;
    }

    @PostMapping(value = "/products")
    public ResponseEntity<?> create(@RequestParam("file") MultipartFile file){
        if(Objects.equals(FilenameUtils.getExtension(file.getOriginalFilename()), "json")) {
            try {
                productService.create(parsingDataService.parseData(file.getInputStream()));
            } catch (NullPointerException | IOException e) {
                LOG.warn("Parse file problems: ", e);
                return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> read(){
        List<Product> products = productService.readAll();
        if(products != null && !products.isEmpty())
            return new ResponseEntity<>(products, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") UUID id, @RequestBody Component component){
        if(productService.update(component, id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") UUID id){
        if(productService.delete(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/products")
    public ResponseEntity<?> delete(){
        productService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
