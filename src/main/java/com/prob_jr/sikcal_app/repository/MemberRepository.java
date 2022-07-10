package com.prob_jr.sikcal_app.repository;

<<<<<<< Updated upstream:src/main/java/com/prob_jr/sikcal_app/repository/MemberRepository.java

import com.prob_jr.sikcal_app.domain.*;
=======
import com.prob_jr.sikcal_app.domain.Entity.Member;
import com.prob_jr.sikcal_app.domain.service.dto.MemberDto;
>>>>>>> Stashed changes:src/main/java/com/prob_jr/sikcal_app/domain/repository/MemberRepository.java
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;


    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
