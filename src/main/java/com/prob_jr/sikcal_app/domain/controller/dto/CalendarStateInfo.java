package com.prob_jr.sikcal_app.domain.controller.dto;

import com.prob_jr.sikcal_app.domain.CalendarStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CalendarStateInfo {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate yesterday;

    private CalendarStatus status;
}
