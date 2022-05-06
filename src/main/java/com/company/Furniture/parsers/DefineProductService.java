package com.company.Furniture.parsers;

import com.company.Furniture.components.furniture.Component;

public interface DefineProductService {
    Class<? extends Component> defineProductByName(String name);
}
