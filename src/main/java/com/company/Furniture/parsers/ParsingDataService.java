package com.company.Furniture.parsers;

import com.company.Furniture.components.furniture.Component;

import java.io.IOException;

public interface ParsingDataService {
    Component parseData(String inputStream) throws IOException;
}
