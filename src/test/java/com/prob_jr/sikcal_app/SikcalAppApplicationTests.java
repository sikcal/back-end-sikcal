package com.prob_jr.sikcal_app;

import com.prob_jr.sikcal_app.domain.*;
import com.prob_jr.sikcal_app.domain.repository.FavoritesRepositoryJpa;
import com.prob_jr.sikcal_app.domain.repository.MemberRepository;
import com.prob_jr.sikcal_app.domain.repository.PostRepository;
import com.prob_jr.sikcal_app.domain.service.dto.RecipeDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.EntityManager;
import java.util.List;

import static com.prob_jr.sikcal_app.domain.QFavorites.favorites;
import static com.prob_jr.sikcal_app.domain.QMember.*;
import static com.prob_jr.sikcal_app.domain.QPost.post;
import static com.prob_jr.sikcal_app.domain.QRecord.record;

@SpringBootTest
@EnableScheduling
class SikcalAppApplicationTests {

//	//@Autowired
//	//EntityManager em;
	@Autowired
	JPAQueryFactory query;
	@Autowired
	FavoritesRepositoryJpa favoritesRepository;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	PostRepository postRepository;


	@Test
	void 테스트() {
		//JPAQueryFactory query = new JPAQueryFactory(em);
		List<Member> results = query
				.selectFrom(member)
				.fetch();
		System.out.println(results);
	}
	@Test
	void 즐겨찾기(){
		Member member = memberRepository.getById("test1234");
		Post post = postRepository.findById(2L);
		Favorites favorites = new Favorites(null,post,member);
		favoritesRepository.save(favorites);
	}
//	@Test
//	void 세타조인테스트(){
//		//JPAQueryFactory query = new JPAQueryFactory(em);
//		List<RecipeDto> results =
//				query.select(Projections.constructor(RecipeDto.class,favorites.member.id, record.kcalInfo, record.requiredFood,post.recipe))
//						.from(favorites, post, record)
//						.where(favorites.member.id.eq("kim12345"),favorites.post.id.eq(post.id),post.record.id.eq(record.id))
//						.fetch();
//		Assertions.assertThat(results.size()==0);
//	}

}
