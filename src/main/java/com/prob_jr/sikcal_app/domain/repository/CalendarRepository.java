package com.prob_jr.sikcal_app.domain.repository;

import com.prob_jr.sikcal_app.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findAllByCalendarDateContains(@Param("date")
                                           @DateTimeFormat(pattern = "yyyy-MM") LocalDate date);
}
