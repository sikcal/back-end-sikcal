package com.prob_jr.sikcal_app.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFood is a Querydsl query type for Food
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFood extends EntityPathBase<Food> {

    private static final long serialVersionUID = -1098857794L;

    public static final QFood food = new QFood("food");

    public final NumberPath<Integer> carbohydrate = createNumber("carbohydrate", Integer.class);

    public final NumberPath<Integer> fat = createNumber("fat", Integer.class);

    public final StringPath foodName = createString("foodName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> protein = createNumber("protein", Integer.class);

    public final NumberPath<Integer> total_kcal = createNumber("total_kcal", Integer.class);

    public QFood(String variable) {
        super(Food.class, forVariable(variable));
    }

    public QFood(Path<? extends Food> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFood(PathMetadata metadata) {
        super(Food.class, metadata);
    }

}

