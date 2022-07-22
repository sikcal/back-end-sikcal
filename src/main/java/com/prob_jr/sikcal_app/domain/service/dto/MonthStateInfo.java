package com.prob_jr.sikcal_app.domain.service.dto;

import com.prob_jr.sikcal_app.domain.CalendarStatus;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class MonthStateInfo {

    private LocalDate localDate;
    private CalendarStatus status;
}
