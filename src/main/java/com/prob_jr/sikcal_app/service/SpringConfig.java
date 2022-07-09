package com.prob_jr.sikcal_app.service;
port org.springframework.beans.factory.annotation.Autowired;
import com.prob_jr.sikcal_app.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    /* 자바 코드로 DI하기  Config에서 bean으로 등록
    DI  생성자주입 !  ! ! ! ! ! ! ! !*/
   /*private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/
    //스프링 데이터 jpa
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }
/*
    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        return new JpaMemberRepository(em);
    }*/
}