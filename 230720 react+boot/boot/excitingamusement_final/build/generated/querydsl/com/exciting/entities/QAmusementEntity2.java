package com.exciting.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAmusementEntity2 is a Querydsl query type for AmusementEntity2
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAmusementEntity2 extends EntityPathBase<AmusementEntity2> {

    private static final long serialVersionUID = -20433271L;

    public static final QAmusementEntity2 amusementEntity2 = new QAmusementEntity2("amusementEntity2");

    public final StringPath a_country = createString("a_country");

    public final StringPath a_img = createString("a_img");

    public final StringPath a_info = createString("a_info");

    public final NumberPath<Double> a_lat = createNumber("a_lat", Double.class);

    public final NumberPath<Double> a_lng = createNumber("a_lng", Double.class);

    public final StringPath a_name = createString("a_name");

    public final StringPath a_note = createString("a_note");

    public final DateTimePath<java.util.Date> a_opening = createDateTime("a_opening", java.util.Date.class);

    public final StringPath a_time = createString("a_time");

    public final NumberPath<Integer> a_view = createNumber("a_view", Integer.class);

    public final NumberPath<Integer> amuse_id = createNumber("amuse_id", Integer.class);

    public final NumberPath<Float> avg_grade = createNumber("avg_grade", Float.class);

    public final NumberPath<Integer> review_cnt = createNumber("review_cnt", Integer.class);

    public QAmusementEntity2(String variable) {
        super(AmusementEntity2.class, forVariable(variable));
    }

    public QAmusementEntity2(Path<? extends AmusementEntity2> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAmusementEntity2(PathMetadata metadata) {
        super(AmusementEntity2.class, metadata);
    }

}

