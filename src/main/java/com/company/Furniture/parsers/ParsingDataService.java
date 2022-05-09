package com.company.Furniture.parsers;

import com.company.Furniture.components.furniture.Component;

import java.io.IOException;
import java.io.InputStream;

public interface ParsingDataService {
    Component parseData(InputStream inputStream) throws IOException;
}
