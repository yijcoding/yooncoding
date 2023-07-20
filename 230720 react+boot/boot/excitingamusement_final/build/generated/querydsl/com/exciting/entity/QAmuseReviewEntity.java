package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAmuseReviewEntity is a Querydsl query type for AmuseReviewEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAmuseReviewEntity extends EntityPathBase<AmuseReviewEntity> {

    private static final long serialVersionUID = -18919035L;

    public static final QAmuseReviewEntity amuseReviewEntity = new QAmuseReviewEntity("amuseReviewEntity");

    public final NumberPath<Integer> amuse_id = createNumber("amuse_id", Integer.class);

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final StringPath member_id = createString("member_id");

    public final StringPath r_content = createString("r_content");

    public final NumberPath<Integer> r_grade = createNumber("r_grade", Integer.class);

    public final StringPath r_regidate = createString("r_regidate");

    public final NumberPath<Integer> ref = createNumber("ref", Integer.class);

    public final NumberPath<Integer> review_id = createNumber("review_id", Integer.class);

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public QAmuseReviewEntity(String variable) {
        super(AmuseReviewEntity.class, forVariable(variable));
    }

    public QAmuseReviewEntity(Path<? extends AmuseReviewEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAmuseReviewEntity(PathMetadata metadata) {
        super(AmuseReviewEntity.class, metadata);
    }

}

