package com.prob_jr.sikcal_app.domain.Entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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