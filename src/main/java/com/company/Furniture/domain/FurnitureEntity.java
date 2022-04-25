package com.company.Furniture.domain;

import lombok.*;

import javax.persistence.*;

@javax.persistence.Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Products")
public class FurnitureEntity {

    @Id
    @Column(name = "prid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "prname")
    private String name;

    @Column(name = "prweight")
    private Integer weight;

    public FurnitureEntity(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }
}
