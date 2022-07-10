package com.prob_jr.sikcal_app.domain.repository;

import com.prob_jr.sikcal_app.domain.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAllByFoodNameContains(String foodName);
}
