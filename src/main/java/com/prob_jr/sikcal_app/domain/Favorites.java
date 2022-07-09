package com.prob_jr.sikcal_app.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorites_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}
