package com.prob_jr.sikcal_app.domain.repository;

import com.prob_jr.sikcal_app.domain.Calendar;
import com.prob_jr.sikcal_app.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findAllByMemberAndCalendarDateBetween(@Param("member") Member member,
                                                         @Param("startDate") LocalDate startDate,
                                                         @Param("endDate") LocalDate endDate);

//    @Query("select c from Calendar c where c.member.id and c.calendarDate")
//    List<Calendar> findAllByYearMonth(@Param("yearMonth") LocalDate date);
}
