package com.prob_jr.sikcal_app.domain.controller.dto;

import com.prob_jr.sikcal_app.domain.service.dto.WeightChangeDto;
import lombok.Getter;

@Getter
public class ChangeWeightRequest {

    private String memberId;
    private int weight;

    public WeightChangeDto toServiceDto() {
        return new WeightChangeDto(memberId, weight);
    }
}
