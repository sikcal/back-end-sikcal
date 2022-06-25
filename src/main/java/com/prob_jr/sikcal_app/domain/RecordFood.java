package com.prob_jr.sikcal_app.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
public class RecordFood {

    @Id
    @GeneratedValue
    @Column(name = "record_food_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "record_id")
    private Record record;

}
