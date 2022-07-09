package com.prob_jr.sikcal_app.domain.service;

import com.prob_jr.sikcal_app.domain.Member;
import com.prob_jr.sikcal_app.domain.MemberActivity;
import com.prob_jr.sikcal_app.domain.MemberGoal;
import com.prob_jr.sikcal_app.domain.MemberSex;
import com.prob_jr.sikcal_app.domain.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setId("sikcal1");
        member.setName("박의수");
        LocalDate date = LocalDate.of(2000, 1, 1);
        member.setHeight(180);
        member.setWeight(75);
        member.setBirth(date);
        member.setSex(MemberSex.MAN);
        member.setActivity(MemberActivity.LIGHT);
        member.setGoal(MemberGoal.REMAIN);

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(saveId));
    }
}