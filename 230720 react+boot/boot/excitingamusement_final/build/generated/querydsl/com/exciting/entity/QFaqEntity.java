package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFaqEntity is a Querydsl query type for FaqEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFaqEntity extends EntityPathBase<FaqEntity> {

    private static final long serialVersionUID = 932061256L;

    public static final QFaqEntity faqEntity = new QFaqEntity("faqEntity");

    public final StringPath content = createString("content");

    public final StringPath f_type = createString("f_type");

    public final NumberPath<Integer> faq_num = createNumber("faq_num", Integer.class);

    public final StringPath title = createString("title");

    public QFaqEntity(String variable) {
        super(FaqEntity.class, forVariable(variable));
    }

    public QFaqEntity(Path<? extends FaqEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFaqEntity(PathMetadata metadata) {
        super(FaqEntity.class, metadata);
    }

}

