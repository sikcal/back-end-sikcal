package com.prob_jr.sikcal_app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorites_id")
    private Long id;

    @OneToOne(fetch = EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name= "member_id")
    private Member member;

    public Favorites(Long id, Post post, Member member) {
        this.id = id;
        this.post = post;
        this.member = member;
    }
}
