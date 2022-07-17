package com.prob_jr.sikcal_app.domain.controller.dto;

import com.prob_jr.sikcal_app.domain.service.dto.FoodRecordDto;
import lombok.Getter;


@Getter
public class CreateRecordRequest {

    private String memberId;

    public FoodRecordDto toServiceDto() {
        return new FoodRecordDto(memberId);
    }
}
