package com.company.Furniture.services;

import com.company.Furniture.components.Furniture.Component;

public interface OrderService {
    Component packingChairOrder(String orderName);
    Component packingTableOrder(String orderName);
    Component packingSofaOrder(String orderName);
}
