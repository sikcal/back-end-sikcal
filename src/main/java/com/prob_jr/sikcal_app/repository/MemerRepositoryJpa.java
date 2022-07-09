package com.prob_jr.sikcal_app.repository;

import com.prob_jr.sikcal_app.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemerRepositoryJpa {
    Member save(Member member);
    Optional<Member> findById(String id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
