package com.prob_jr.sikcal_app.domain.repository;

import com.prob_jr.sikcal_app.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
