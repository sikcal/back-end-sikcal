package com.prob_jr.sikcal_app.domain.repository;

import com.prob_jr.sikcal_app.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, String> {

}
