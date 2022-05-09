package com.company.Furniture.parsers;

import com.company.Furniture.ApplicationController;
import com.company.Furniture.components.furniture.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

@Service
@Slf4j
public class ParsingJSONServiceImpl implements ParsingDataService {
    private static final Logger LOG = LoggerFactory.getLogger(ParsingJSONServiceImpl.class);
    private final ObjectMapper objectMapper;

    public ParsingJSONServiceImpl(){
        objectMapper = new ObjectMapper();
    }

    @Override
    public Component parseData(InputStream inputStream) throws IOException {
        JsonNode parent= new ObjectMapper().readTree(inputStream);
        String className = parent.path("name").asText();
        try{
            DefineProductService defineProductService = new DefineProductServiceImpl();
            Class<? extends Component> type = defineProductService.defineProductByName(className);
            return objectMapper.readValue(parent.toPrettyString(), type);
        } catch (IllegalArgumentException e){
            LOG.warn("Class name is not correct: ", e);
            return null;
        }
    }
}