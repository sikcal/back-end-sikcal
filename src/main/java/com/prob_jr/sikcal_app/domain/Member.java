package com.prob_jr.sikcal_app.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_pw")
    private String password;

    private String name;

    private int height;
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private MemberSex sex;

    @Enumerated(EnumType.STRING)
    private MemberActivity activity;

    @Enumerated(EnumType.STRING)
    private MemberGoal goal;

    private int weight;

}
