package com.prob_jr.sikcal_app.domain.repository;


import com.prob_jr.sikcal_app.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,String> {

    Optional<Member> findByName(String name);
    //List<Member> findAll();
    boolean existsById(String id);

    //boolean existsByName(String name);
    /*
    @Override
    default Optional<Member> findById(String id) {
        return Optional.empty();
    }

    @Override
    Optional<Member> findByName(String name);
    */

}
