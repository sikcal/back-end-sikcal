package com.prob_jr.sikcal_app.domain.controller.dto;

import com.prob_jr.sikcal_app.domain.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class FoodInfoResponse {

    private String foodName;

    public static FoodInfoResponse from(Food food) {
        return new FoodInfoResponse(food.getFoodName());
    }
}
