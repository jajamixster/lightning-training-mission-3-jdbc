package com.mission3.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import javax.persistence.*;


@Data
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
