package com.prob_jr.sikcal_app.domain.service;

import com.prob_jr.sikcal_app.domain.controller.MemberController;
import com.prob_jr.sikcal_app.domain.repository.FavoritesRepository;
import com.prob_jr.sikcal_app.domain.service.dto.RecipeDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FavoritesService {


    private final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
    private final FavoritesRepository repository;

    public List<RecipeDto> MyFavorites(String id){
        return repository.MyFavorites(id);
    }


}
