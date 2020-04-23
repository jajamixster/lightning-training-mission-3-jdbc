package com.mission3.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private boolean isExotic;

    @Column(nullable = false)
    private double price;
}
