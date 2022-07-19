package com.prob_jr.sikcal_app.domain.controller.dto;

import com.prob_jr.sikcal_app.domain.service.dto.TargetKcalCheckDto;
import lombok.Getter;

@Getter
public class CheckTargetRequest {

    private String memberId;

    public TargetKcalCheckDto toServiceDto() {
        return new TargetKcalCheckDto(memberId);
    }
}
