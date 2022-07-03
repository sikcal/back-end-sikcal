package com.prob_jr.sikcal_app.domain.repository;

import com.prob_jr.sikcal_app.domain.Food;
import com.prob_jr.sikcal_app.domain.Record;
import com.prob_jr.sikcal_app.domain.RecordFood;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecordRepository {

    private final EntityManager em;

    public void save(Record record) {
        em.persist(record);
    }

    public Record findOne(Long id) {
        return em.find(Record.class, id);
    }

    public List<RecordFood> findAll(FoodSearch foodSearch) {

        return em.createQuery("select rf from RecordFood rf join rf.food f" +
                        " where f.foodName = :name", RecordFood.class)
                .setParameter("name", foodSearch.getFoodName())
                .setMaxResults(100)
                .getResultList();
    }

    public void cancel(Record record) {
        em.remove(record);
    }
}
