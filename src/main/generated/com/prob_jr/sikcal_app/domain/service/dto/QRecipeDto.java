package com.prob_jr.sikcal_app.domain.service.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.prob_jr.sikcal_app.domain.service.dto.QRecipeDto is a Querydsl Projection type for RecipeDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QRecipeDto extends ConstructorExpression<RecipeDto> {

    private static final long serialVersionUID = 130923081L;

    public QRecipeDto(com.querydsl.core.types.Expression<String> memberid, com.querydsl.core.types.Expression<String> totalkcal, com.querydsl.core.types.Expression<String> requiredfood, com.querydsl.core.types.Expression<String> recipe) {
        super(RecipeDto.class, new Class<?>[]{String.class, String.class, String.class, String.class}, memberid, totalkcal, requiredfood, recipe);
    }

}

