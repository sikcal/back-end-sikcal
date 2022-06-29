package com.prob_jr.sikcal_app.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Record {

    @Id
    @GeneratedValue
    @Column(name = "record_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL)
    private List<RecordFood> recordFoods = new ArrayList<>();

    private LocalDateTime recordDate;

    public void setMember(Member member) {
        this.member = member;
    }

    //====연관관계 편의 메소드====//
    public void addRecordFood(RecordFood recordFood) {
        recordFoods.add(recordFood);
        recordFood.setRecord(this);
    }

    //====생성 메서드====//
    public static Record createRecord(Member member, RecordFood... recordFoods) {
        Record record = new Record();
        record.setMember(member);
        for (RecordFood recordFood : recordFoods) {
            record.addRecordFood(recordFood);
        }
        return record;
    }

}
