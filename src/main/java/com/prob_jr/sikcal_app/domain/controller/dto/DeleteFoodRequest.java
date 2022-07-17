package com.prob_jr.sikcal_app.domain.controller.dto;

import com.prob_jr.sikcal_app.domain.service.dto.FoodDeleteDto;
import lombok.Getter;

@Getter
public class DeleteFoodRequest {

    private Long recordId;
    private Long recordFoodId;

    public FoodDeleteDto toServiceDto() {
        return new FoodDeleteDto(recordId, recordFoodId);
    }
}
