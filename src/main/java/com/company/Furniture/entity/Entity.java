package com.company.Furniture.entity;

import lombok.*;

import javax.persistence.*;

@javax.persistence.Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="furniture")
public class Entity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private Integer weight;

    public Entity(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }
}
