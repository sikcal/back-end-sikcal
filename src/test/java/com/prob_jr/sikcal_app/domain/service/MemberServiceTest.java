package com.prob_jr.sikcal_app.domain.service;

import com.prob_jr.sikcal_app.domain.Member;

import com.prob_jr.sikcal_app.repository.MemberRepository;
import com.prob_jr.sikcal_app.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    MemberRepository memberRepository;
    EntityManager em;

    @Test
    @Rollback(value = false)
    public void join() throws Exception {
        //given
        Member member = new Member();
        member.setId(6L);
        member.setName("박의수");
        LocalDate date = LocalDate.of(2000, 1, 1);
        member.setHeight(180);
        member.setWeight(75);
        member.setBirth(date);
        member.setSex(com.prob_jr.sikcal_app.domain.MemberSex.MAN);
        //member.setActivity(com.prob_jr.sikcal_app.domain.MemberActivity.HARD);
       // member.setGoal(com.prob_jr.sikcal_app.domain.MemberGoal.GAIN);

        //when
        //Long saveId = memberService.join(member);
        memberService.join(member);

        //then
       // assertEquals(member, memberRepository.findOne(saveId));
    }
}