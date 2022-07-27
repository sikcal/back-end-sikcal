package com.prob_jr.sikcal_app.domain.repository;


import com.prob_jr.sikcal_app.domain.Favorites;
import com.prob_jr.sikcal_app.domain.service.dto.RecipeDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.prob_jr.sikcal_app.domain.QFavorites.favorites;
import static com.prob_jr.sikcal_app.domain.QPost.post;
import static com.prob_jr.sikcal_app.domain.QRecord.record;

//querydsl을 이용한 여러테이블 조인
@Repository
public interface FavoritesRepositoryJpa extends JpaRepository<Favorites,String> {

}
