package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAmusement is a Querydsl query type for Amusement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAmusement extends EntityPathBase<Amusement> {

    private static final long serialVersionUID = 1381750632L;

    public static final QAmusement amusement = new QAmusement("amusement");

    public final StringPath a_country = createString("a_country");

    public final StringPath a_img = createString("a_img");

    public final StringPath a_info = createString("a_info");

    public final NumberPath<Float> a_lat = createNumber("a_lat", Float.class);

    public final NumberPath<Float> a_lng = createNumber("a_lng", Float.class);

    public final StringPath a_name = createString("a_name");

    public final StringPath a_note = createString("a_note");

    public final DatePath<java.sql.Date> a_opening = createDate("a_opening", java.sql.Date.class);

    public final StringPath a_time = createString("a_time");

    public final NumberPath<Integer> a_view = createNumber("a_view", Integer.class);

    public final NumberPath<Integer> amuse_id = createNumber("amuse_id", Integer.class);

    public final NumberPath<Float> avg_grade = createNumber("avg_grade", Float.class);

    public final NumberPath<Integer> review_cnt = createNumber("review_cnt", Integer.class);

    public QAmusement(String variable) {
        super(Amusement.class, forVariable(variable));
    }

    public QAmusement(Path<? extends Amusement> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAmusement(PathMetadata metadata) {
        super(Amusement.class, metadata);
    }

}

