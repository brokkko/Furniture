package com.company.Furniture.services;

import com.company.Furniture.components.Furniture.Component;

public interface DataParserService {
    Component unpackingChairOrder(String orderName);
    Component unpackingTableOrder(String orderName);
    Component unpackingSofaOrder(String orderName);
}
