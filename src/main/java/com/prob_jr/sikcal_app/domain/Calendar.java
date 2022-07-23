package com.prob_jr.sikcal_app.domain;

import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "UniqueDateAndMember", columnNames = {"calendar_date", "member_id"})})
@Getter
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    private Long id;

    @Column(name = "calendar_date")
    private LocalDate calendarDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private CalendarStatus status;

    private String image;

    public void setMember(Member member) {
        this.member = member;
    }

    public void setCalendarDate(LocalDate calendarDate) {
        this.calendarDate = calendarDate;
    }

    public void setStatus(CalendarStatus status) {
        this.status = status;
    }

    //====생성 메서드====//
    public static Calendar createCalendar(Member member, LocalDate createdDate) {
        Calendar calendar = new Calendar();
        calendar.setMember(member);
        calendar.setCalendarDate(createdDate);

        return calendar;
    }

    //====목표 섭취 칼로리 달성여부 업데이트====//
    public static Calendar updateStatus(Calendar calendar, CalendarStatus status) {
        calendar.setStatus(status);

        return calendar;
    }

}
