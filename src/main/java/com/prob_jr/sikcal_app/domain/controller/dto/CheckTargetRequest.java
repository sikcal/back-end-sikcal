package com.prob_jr.sikcal_app.domain.controller.dto;

import com.prob_jr.sikcal_app.domain.service.dto.MonthlyTargetDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CheckTargetRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate yearMonth;

    public MonthlyTargetDto toServiceDto() {
        return new MonthlyTargetDto(yearMonth);
    }
}
