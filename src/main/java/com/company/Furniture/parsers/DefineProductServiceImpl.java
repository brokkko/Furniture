package com.company.Furniture.parsers;

import com.company.Furniture.components.furniture.Chair;
import com.company.Furniture.components.furniture.Component;
import com.company.Furniture.components.furniture.Sofa;
import com.company.Furniture.components.furniture.Table;
import com.company.Furniture.components.types.TypeProduct;

public class DefineProductServiceImpl implements DefineProductService{
    @Override
    public Class<? extends Component> defineProductByName(String name){
        String[] parts = name.split("\\.");
        TypeProduct productName = TypeProduct.valueOf(parts[parts.length - 1].toUpperCase());
        switch (productName) {
            case  CHAIR:
                return Chair.class;
            case TABLE:
                return Table.class;
            case SOFA:
                return Sofa.class;
            default:
                throw new IllegalArgumentException();
        }
    }
}
