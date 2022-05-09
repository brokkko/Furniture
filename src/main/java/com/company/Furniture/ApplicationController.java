package com.company.Furniture;

import com.company.Furniture.components.options.CalculateService;
import com.company.Furniture.entities.Product;
import com.company.Furniture.parsers.ParsingDataService;
import com.company.Furniture.repositories.ConnectingDBService;
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
public class ApplicationController {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private CalculateService calculateService;

    @Autowired
    private ConnectingDBService connectingDBService;

    @Autowired
    private ParsingDataService parsingDataService;

    @GetMapping
    public String home(Model model){
        Iterable<Product> products = connectingDBService.findAllElements();
        model.addAttribute("history", products);
        return "home";
    }

    @PostMapping
    public String addProduct(@RequestParam String name,
                             @RequestParam int weight,
                             Model model
    ){
        connectingDBService.saveByParameters(name, weight);
        Iterable<Product> products = connectingDBService.findAllElements();
        model.addAttribute("history", products);
        return "home";
    }

    @PostMapping("loadFromFile")
    public String loadFromFile(Model model,
                             @RequestParam("file") MultipartFile file
    ) {
        if(Objects.equals(FilenameUtils.getExtension(file.getOriginalFilename()), "json")) {
            try {
                connectingDBService.saveElement(parsingDataService.parseData(file.getInputStream()));
            } catch (NullPointerException | IOException e) {
                LOG.warn("Parse file problems: ", e);
            }
        }
        Iterable<Product> products = connectingDBService.findAllElements();
        model.addAttribute("history", products);
        return "home";
    }

    @PostMapping("deleteProduct")
    public String deleteProduct(@RequestParam String name, Model model){
        connectingDBService.deleteByName(name);
        Iterable<Product> products = connectingDBService.findAllElements();
        model.addAttribute("history", products);
        return "home";
    }

}
