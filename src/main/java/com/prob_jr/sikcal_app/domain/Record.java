package com.prob_jr.sikcal_app.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL)
    private List<RecordFood> recordFoods = new ArrayList<>();

    private LocalDate recordDate;

    private int totalKcal;

    private String requiredFood; //하나의 식단을 구성하는 음식을 concatenate하여 저장할 컬럼

    public void setMember(Member member) {
        this.member = member;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    //====연관관계 편의 메소드====//
    public void addRecordFood(RecordFood recordFood) {
        recordFoods.add(recordFood);
        recordFood.setRecord(this);
    }

    //====생성 메서드====//
    public static Record createRecord(Member member) {
        Record record = new Record();
        record.setMember(member);
        record.setRecordDate(LocalDate.now()); //식단생성과 함께 생성 당시의 date를 set

        return record;
    }

}
