package com.company.Furniture.controllers;

import com.company.Furniture.components.options.CalculateService;
import com.company.Furniture.parsers.ParsingDataService;
import com.company.Furniture.domain.products.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import org.slf4j.Logger;

@Controller
@Slf4j
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private CalculateService calculateService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ParsingDataService parsingDataService;

    @GetMapping
    public String homePage(Model model){
        return "homePage";
    }

    @PostMapping("getProductList")
    public String getProducts(Model model){
        model.addAttribute("products", productService.findAll());
        return "productListPage";
    }

    @PostMapping("return")
    public String returnHomePage(Model model){
        return "homePage";
    }

    @PostMapping("loadFromFile")
    public String loadFromFile(Model model,
                             @RequestParam("file") MultipartFile file
    ) {
        if(Objects.equals(FilenameUtils.getExtension(file.getOriginalFilename()), "json")) {
            try {
                productService.save(parsingDataService.parseData(file.getInputStream()));
            } catch (NullPointerException | IOException e) {
                LOG.warn("Parse file problems: ", e);
            }
        }
        return "homePage";
    }

    @DeleteMapping("deleteProduct")
    public String deleteProduct(@RequestParam String name, Model model){
        productService.deleteByName(name);
        return "homePage";
    }

}
