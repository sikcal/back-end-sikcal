package com.prob_jr.sikcal_app.domain.controller.dto;

import com.prob_jr.sikcal_app.domain.service.dto.RecordFoodSearchCond;
import lombok.Getter;

@Getter
public class RecordFoodSearchRequest {

    private String foodName;

    public RecordFoodSearchCond toServiceDto() {
        return new RecordFoodSearchCond(foodName);
    }
}
