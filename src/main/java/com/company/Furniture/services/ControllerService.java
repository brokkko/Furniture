package com.company.Furniture.services;

import java.util.List;

public interface ControllerService {
    void saveOrder(List<String> file);
    int findOrderWeightByName(String name);
}
