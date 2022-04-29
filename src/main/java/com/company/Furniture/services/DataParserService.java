package com.company.Furniture.services;

import com.company.Furniture.components.furniture.Component;

public interface DataParserService {
    Component unpackingOrder(String orderFile);
}
