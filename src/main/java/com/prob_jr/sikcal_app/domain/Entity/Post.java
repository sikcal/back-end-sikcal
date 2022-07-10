package com.prob_jr.sikcal_app.domain.Entity;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String menu;
    private Long numOfLike;
    private String recipe;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "record_id")
    private Record record;

}
