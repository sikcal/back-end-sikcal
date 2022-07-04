package com.prob_jr.sikcal_app.domain.repository;

import com.prob_jr.sikcal_app.domain.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FoodRepository {

    private EntityManager em;

    public Food findOne(Long id) {
        return em.find(Food.class, id);
    }

    public List<Food> findAll() {

        return em.createQuery("select f from Food f", Food.class)
                .getResultList();
    }

}
