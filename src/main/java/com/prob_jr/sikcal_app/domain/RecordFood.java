package com.prob_jr.sikcal_app.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class RecordFood {

    @Id
    @GeneratedValue
    @Column(name = "record_food_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "record_id")
    private Record record;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    public void setFood(Food food) {
        this.food = food;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    //====생성 메서드====//
    public static RecordFood createRecordFood(Record record, Food food) {
        RecordFood recordFood = new RecordFood();
        recordFood.setRecord(record);
        recordFood.setFood(food);

        return recordFood;
    }

    //====비즈니스 로직====//
    public void cancel() {

    }

}
