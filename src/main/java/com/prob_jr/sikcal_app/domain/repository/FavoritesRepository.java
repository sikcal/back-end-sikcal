package com.prob_jr.sikcal_app.domain.repository;


import com.prob_jr.sikcal_app.domain.service.dto.RecipeDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.prob_jr.sikcal_app.domain.QFavorites.favorites;
import static com.prob_jr.sikcal_app.domain.QPost.post;
import static com.prob_jr.sikcal_app.domain.QRecord.record;

//querydsl을 이용한 여러테이블 조인
@Repository
public class FavoritesRepository {

    @Autowired
    JPAQueryFactory query;

    /**
     * 내 즐겨찾기 리스트
     */
    public List<RecipeDto> MyFavorites(String id) {
        List<RecipeDto> results =
                query.select(Projections.constructor(RecipeDto.class, favorites.member.id,post.menu,record.totalCarbohydrate,record.totalFat,record.totalFat, record.totalKcal, record.requiredFood, post.recipe, post.picUri))
                        .from(favorites, post, record)
                        .where(favorites.member.id.eq(id), favorites.post.id.eq(post.id), post.record.id.eq(record.id))
                        .fetch();

        return results;


    }
}
