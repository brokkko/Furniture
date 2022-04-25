package com.company.Furniture.entities;

import lombok.*;

import javax.persistence.*;

@javax.persistence.Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="products")
public class Furniture {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "weight")
    private Integer weight;

    public Furniture(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }
}
