package com.company.Furniture.components.types;

import lombok.Getter;

@Getter
public enum TypeProduct {
    CHAIR("Chair"),
    TABLE("Table"),
    SOFA("Sofa"),
    CUPBOARD("Cupboard");

    private final String name; // названия продуктов
    TypeProduct(String name) {
        this.name = name;
    }
    }
