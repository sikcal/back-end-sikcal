package com.prob_jr.sikcal_app.domain.repository;

//Querydsl 을 쓰기 위한 ->jpaRepo로는 못하는 작업들을 하기 위함

import com.prob_jr.sikcal_app.domain.service.dto.RecipeDto;

import java.util.List;

public interface MyPostRepoCustom {
    List<RecipeDto> findMyFavorites(String memberid);
    //커뮤기능
    //Page<RecipeDto> bestRecipeList(SearchForm searchForm, Pageable pageable);
}
