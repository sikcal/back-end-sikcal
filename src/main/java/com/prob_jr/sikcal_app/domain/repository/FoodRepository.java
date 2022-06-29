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

    public void save(Food food) {
        em.persist(food);
    }

    public Food findOne(String name) {
        return em.find(Food.class, name);
    }

    public List<Food> findAll() {

        return em.createQuery("select f from Food f", Food.class)
                .getResultList();
    }

}
