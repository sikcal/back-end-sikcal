package com.prob_jr.sikcal_app.domain.controller.dto;

import com.prob_jr.sikcal_app.domain.service.dto.FoodSaveDto;
import lombok.Getter;

@Getter
public class SaveFoodRequest {

    private Long recordId;
    private Long foodId;

    public FoodSaveDto toServiceDto() {
        return new FoodSaveDto(recordId, foodId);
    }
}
