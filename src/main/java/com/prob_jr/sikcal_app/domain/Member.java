package com.prob_jr.sikcal_app.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter //회원이 정상적으로 테이블에 insert되는지를 확인하기 위해 열어둔 Setter || 추후 삭제 예정
public class Member {

    @Id
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
