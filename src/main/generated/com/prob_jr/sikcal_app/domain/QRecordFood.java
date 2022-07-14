package com.prob_jr.sikcal_app.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecordFood is a Querydsl query type for RecordFood
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecordFood extends EntityPathBase<RecordFood> {

    private static final long serialVersionUID = 1921895087L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecordFood recordFood = new QRecordFood("recordFood");

    public final QFood food;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRecord record;

    public QRecordFood(String variable) {
        this(RecordFood.class, forVariable(variable), INITS);
    }

    public QRecordFood(Path<? extends RecordFood> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecordFood(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecordFood(PathMetadata metadata, PathInits inits) {
        this(RecordFood.class, metadata, inits);
    }

    public QRecordFood(Class<? extends RecordFood> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.food = inits.isInitialized("food") ? new QFood(forProperty("food")) : null;
        this.record = inits.isInitialized("record") ? new QRecord(forProperty("record"), inits.get("record")) : null;
    }

}

