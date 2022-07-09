package com.prob_jr.sikcal_app.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Food {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long id;

    @Column(name = "food_name")
    private String foodName;

    private int carbohydrate;
    private int protein;
    private int fat;
    private int total_kcal;
}