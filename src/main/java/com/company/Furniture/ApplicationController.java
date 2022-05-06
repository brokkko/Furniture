package com.company.Furniture;

import com.company.Furniture.components.furniture.Component;
import com.company.Furniture.components.options.CalculateService;
import com.company.Furniture.components.options.CalculateServiceImpl;
import com.company.Furniture.components.types.TypeUnits;
import com.company.Furniture.entities.Product;
import com.company.Furniture.parsers.ParsingDataService;
import com.company.Furniture.parsers.ParsingJSONServiceImpl;
import com.company.Furniture.repositories.RepositoryService;
import com.company.Furniture.services.*;
import com.company.Furniture.storage.FileSystemStorageService;
import com.company.Furniture.storage.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class ApplicationController {

    private final Logger LOG = Logger.getLogger(ApplicationController.class.getName());

    @Autowired
    private RepositoryService repositoryService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public String home(Map<String, Object> model){
        Iterable<Product> products = repositoryService.findAllElements();

        model.put("history", products);
        return "home";
    }

    @PostMapping
    public String addProduct(@RequestParam String name,
                             @RequestParam int weight,
                             Map<String, Object> model
    ){
        repositoryService.saveElement(new Product(name, weight));
        Iterable<Product> products = repositoryService.findAllElements();
        model.put("history", products);
        return "home";
    }

    @PostMapping("loadFromFile")
    public String loadFromFile(Map<String, Object> model,
                             @RequestParam("file") MultipartFile file
    ) {

        // TODO: здесь должен быть только один метод, обрабатывающий данные

        if(Objects.equals(FilenameUtils.getExtension(file.getOriginalFilename()), "json")) {

            StorageService storageService = new FileSystemStorageService(this.uploadPath);
            InputStreamService inputStreamService = new InputStreamServiceImpl();
            CalculateService calculateService = new CalculateServiceImpl();
            ParsingDataService parsingDataService = new ParsingJSONServiceImpl();

            try {
                String path = storageService.store(file);
                String inputStream = inputStreamService.openInputStream(new File(path));
                Component component = parsingDataService.parseData(inputStream);
                repositoryService.saveElement(new Product(component.getName(), (int) calculateService.getWeight(component, TypeUnits.GRAMS)));
            } catch (NullPointerException | IOException e) {
                // TODO: delete file from dir
                LOG.log(Level.WARNING, "Parse file problems: ", e);
            }
        }

        Iterable<Product> products = repositoryService.findAllElements();
        model.put("history", products);
        return "home";
    }

    @PostMapping("deleteProduct")
    public String deleteProduct(@RequestParam String name, Map<String, Object> model){
        Iterable<Product> iterator = repositoryService.findAllElements();
        List<Product> productList = new ArrayList<>();
        iterator.forEach(productList :: add);
        List<Product> deleteList = new ArrayList<>();
        for(Product product : productList){
            if(Objects.equals(product.getName(), name))
                deleteList.add(product);
        }
        repositoryService.deleteByName(deleteList);
        Iterable<Product> products = repositoryService.findAllElements();
        model.put("history", products);
        return "home";
    }

}
