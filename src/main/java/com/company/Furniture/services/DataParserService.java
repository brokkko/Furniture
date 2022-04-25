package com.company.Furniture.services;

import com.company.Furniture.components.furniture.Component;

public interface DataParserService {
    Component unpackingChairOrder(String orderName);
    Component unpackingTableOrder(String orderName);
    Component unpackingSofaOrder(String orderName);
}
