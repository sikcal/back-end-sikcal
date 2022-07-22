package com.prob_jr.sikcal_app.domain.controller.dto;

import com.prob_jr.sikcal_app.domain.Calendar;
import com.prob_jr.sikcal_app.domain.CalendarStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class MonthlyStateInfo {

    private LocalDate date;
    private CalendarStatus status;

    public static MonthlyStateInfo from(Calendar calendar) {
        return new MonthlyStateInfo(calendar.getCalendarDate(),
                calendar.getStatus());
    }
}
