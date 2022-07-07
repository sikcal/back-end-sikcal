package com.prob_jr.sikcal_app.domain.controller.dto;

import com.prob_jr.sikcal_app.domain.Food;
import com.prob_jr.sikcal_app.domain.RecordFood;
import com.prob_jr.sikcal_app.domain.service.dto.RecordFoodSearchCond;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class FoodInfoResponse {

    private String foodName;

    public static FoodInfoResponse from(RecordFoodSearchCond recordFoodSearchCond) {
        return new FoodInfoResponse(recordFoodSearchCond.getFoodName());
    }
}
