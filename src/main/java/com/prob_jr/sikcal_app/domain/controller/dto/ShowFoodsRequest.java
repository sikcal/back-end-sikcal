package com.prob_jr.sikcal_app.domain.controller.dto;

import com.prob_jr.sikcal_app.domain.service.dto.FoodShowCond;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShowFoodsRequest {

    private Long recordId;

    public FoodShowCond toServiceDto() {
        return new FoodShowCond(recordId);
    }
}
