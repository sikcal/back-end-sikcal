package com.prob_jr.sikcal_app.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Record {

    @Id
    @GeneratedValue
    @Column(name = "record_id")
    private Long id;

    private LocalDateTime recordDate;

    private int foodId;

    private String foodName;

    private int carbohydrate;
    private int protein;
    private int fat;
    private int total_cal;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
