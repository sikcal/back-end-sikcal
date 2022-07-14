package com.prob_jr.sikcal_app.domain;

import com.prob_jr.sikcal_app.domain.service.dto.MemberDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
//@Getter
//@Setter //회원이 정상적으로 테이블에 insert되는지를 확인하기 위해 열어둔 Setter || 추후 삭제 예정
public class Member {


    @Id
    @Column(name = "member_id")
    private String id;

    @Column(name = "member_pw")
    private String password;

    private String name;

    private int height;

    @DateTimeFormat(pattern= "yyyy-MM-dd")
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private MemberSex sex;

    @Enumerated(EnumType.STRING)
    private MemberActivity activity;

    @Enumerated(EnumType.STRING)
    private MemberGoal goal;

    private int weight;
    //id 이름 pw 역할 로 ArrayList
    //사용자를 가져올때 role 역할도 갖고오고싶음 -> Eager
    //Lazy 지연로딩을 할 시 select두번 나감 비효율적
    //Eager 즉시 로딩은 jpa가 join을 이용해 한방 쿼리로 다 조회해옴 개꿀
    @ManyToMany(fetch=FetchType.EAGER)
    @Builder.Default
    private Collection<Role> roles= new ArrayList<>();
//1:28
    //member에서 dto로
    public MemberDto toDto(){
        return MemberDto.builder().id(id)
                .activity(activity)
                .birth(birth)
                .goal(goal)
                .height(height)
                .name(name)
                .password(password)
                .sex(sex)
                .weight(weight)
                .roles(roles)
                .build();
    }



}
