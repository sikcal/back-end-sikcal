package com.prob_jr.sikcal_app.domain.repository;

import com.prob_jr.sikcal_app.domain.RecordFood;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecordFoodRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public void save(RecordFood recordFood) {
        em.persist(recordFood);
    }

    public RecordFood findOne(Long id) {
        return em.find(RecordFood.class, id);
    }

    public List<RecordFood> findFoods(Long recordId) {
        return em.createQuery("select rf from RecordFood rf where rf.record.id =:recordId", RecordFood.class)
                .setParameter("recordId",recordId)
                .getResultList();
    }

    public void delete(RecordFood recordFood) {
        em.remove(recordFood);
    }
}
