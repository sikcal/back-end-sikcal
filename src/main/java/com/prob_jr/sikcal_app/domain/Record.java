package com.prob_jr.sikcal_app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDate recordDate; //식단을 추가한 날짜를 저장할 컬럼

    //식단의 탄단지 및 담긴 음식 전체 칼로리를 저장할 컬럼
    private int totalCarbohydrate;
    private int totalProtein;
    private int totalFat;
    private int totalKcal;

    //@JsonIgnore
    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL)
    private List<RecordFood> recordFoods = new ArrayList<>();


    public void setTotalCarbohydrate(int totalCarbohydrate) {
        this.totalCarbohydrate = totalCarbohydrate;
    }

    public void setTotalProtein(int totalProtein) {
        this.totalProtein = totalProtein;
    }

    public void setTotalFat(int totalFat) {
        this.totalFat = totalFat;
    }

    public void setTotalKcal(int totalKcal) {
        this.totalKcal = totalKcal;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setRecordFoods(List<RecordFood> recordFoods) {
        this.recordFoods = recordFoods;
    }

    //====생성 메서드====//
    public static Record createRecord(Member member) {
        Record record = new Record();
        record.setMember(member);
        record.setRecordDate(LocalDate.now()); //식단생성과 함께 생성 당시의 date를 set

        //식단의 총 탄단지 및 총 칼로리를 초기값인 0으로 설정
        record.setTotalCarbohydrate(0);
        record.setTotalProtein(0);
        record.setTotalFat(0);
        record.setTotalKcal(0);

        return record;
    }

}
