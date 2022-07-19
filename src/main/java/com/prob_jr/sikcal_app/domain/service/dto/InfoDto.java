package com.prob_jr.sikcal_app.domain.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class InfoDto {
    int requiredTan;
    int requiredDan;
    int requiredJi;
    int requiredTotal;

}

