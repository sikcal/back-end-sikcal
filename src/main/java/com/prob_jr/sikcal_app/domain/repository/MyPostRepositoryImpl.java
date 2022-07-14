package com.prob_jr.sikcal_app.domain.repository;

import com.prob_jr.sikcal_app.domain.QFavorites;
import com.prob_jr.sikcal_app.domain.QMember;
import com.prob_jr.sikcal_app.domain.QPost;
import com.prob_jr.sikcal_app.domain.QRecord;
import com.prob_jr.sikcal_app.domain.service.dto.QRecipeDto;
import com.prob_jr.sikcal_app.domain.service.dto.RecipeDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Projection;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.prob_jr.sikcal_app.domain.QMember.member;

@Repository
@RequiredArgsConstructor
public class MyPostRepositoryImpl implements MyPostRepoCustom{
    private final JPAQueryFactory queryFactory;
    //post로 저장해놓은것들만 뜸
    //favorites table에 post id로
    //memberid로 시작해서
    @Override
    public List<RecipeDto> findMyFavorites(String memberid) {
        QMember member = QMember.member;
        QPost post = QPost.post;
        QFavorites favorites= QFavorites.favorites;
        QRecord record = QRecord.record;

        /*
        List<RecipeDto> results=queryFactory
                .select(record.id,post.recipe,record.requiredFood,record.totalKcal)
                .from(post,favorites,record)
                .where(favorites.member.id.eq(memberid),favorites.post.eq(post),post.record.eq(record))
                .fetch(Projections.fields(RecipeDto.class, re));
        QueryResults<RecipeDto> result = queryFactory
                .select(record.id,post.recipe,record.requiredFood,record.totalKcal)
                .from(favorites,post,record)
                .leftJoin(favorites.post, post)
                .fetchJoin()
                .leftJoin(post.record,record)
                .fetchJoin()
                .where(favorites.member.id.eq(memberid))
                .fetchResults(Projections.fields(RecipeDto.class,));*/
        return null;
    }
}
