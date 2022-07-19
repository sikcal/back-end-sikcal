package com.prob_jr.sikcal_app.domain.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FoodDeleteDto {

    private Long recordId;
    private Long recordFoodId;

}
