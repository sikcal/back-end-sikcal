package com.prob_jr.sikcal_app.domain.repository;

import com.prob_jr.sikcal_app.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAllByFoodNameContains(@Param("foodName") String foodName);


}
