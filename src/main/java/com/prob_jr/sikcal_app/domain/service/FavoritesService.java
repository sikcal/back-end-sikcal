package com.prob_jr.sikcal_app.domain.service;

import com.prob_jr.sikcal_app.domain.Favorites;
import com.prob_jr.sikcal_app.domain.Member;
import com.prob_jr.sikcal_app.domain.Post;
import com.prob_jr.sikcal_app.domain.controller.MemberController;
import com.prob_jr.sikcal_app.domain.repository.FavoritesRepository;
import com.prob_jr.sikcal_app.domain.repository.FavoritesRepositoryJpa;
import com.prob_jr.sikcal_app.domain.repository.MemberRepository;
import com.prob_jr.sikcal_app.domain.repository.PostRepository;
import com.prob_jr.sikcal_app.domain.service.dto.RecipeDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FavoritesService {


    private final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
    private final FavoritesRepository repository;
    private final FavoritesRepositoryJpa favoritesRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public List<RecipeDto> MyFavorites(String id){
        return repository.MyFavorites(id);
    }

    public Favorites addFavorites(String id, Long postId){
        Member member = memberRepository.getById(id);
        Post post = postRepository.findById(postId);
        Favorites favorites = new Favorites(null,post,member);
        return favoritesRepository.save(favorites);
    }


}
