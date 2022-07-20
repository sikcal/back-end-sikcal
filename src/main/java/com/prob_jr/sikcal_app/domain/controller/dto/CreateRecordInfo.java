package com.prob_jr.sikcal_app.domain.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreateRecordInfo {

    private Long recordId;
    private LocalDate createdDate;
}
