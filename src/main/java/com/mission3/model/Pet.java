package com.mission3.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "name is mandatory field. please provide name for a pet")
    @Column(nullable = false)
    private String name;

    @Size(min=2, message="Description should have at least 2 characters")
    @Column
    private String description;

    @Column(nullable = false)
    private boolean isExotic;

    @Column(nullable = false)
    private double price;
}
