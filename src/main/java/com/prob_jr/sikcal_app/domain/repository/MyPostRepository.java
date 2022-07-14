package com.prob_jr.sikcal_app.domain.repository;

import com.prob_jr.sikcal_app.domain.service.dto.RecipeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyPostRepository extends JpaRepository<RecipeDto,String> {
    //List<RecipeDto> findMyFavorites(String id);
    //커뮤기능
    //Page<RecipeDto> bestRecipeList(SearchForm searchForm, Pageable pageable);
}
