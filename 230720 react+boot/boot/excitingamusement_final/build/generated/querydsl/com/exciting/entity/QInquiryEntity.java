package com.exciting.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInquiryEntity is a Querydsl query type for InquiryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInquiryEntity extends EntityPathBase<InquiryEntity> {

    private static final long serialVersionUID = -847567175L;

    public static final QInquiryEntity inquiryEntity = new QInquiryEntity("inquiryEntity");

    public final StringPath b_content = createString("b_content");

    public final StringPath b_title = createString("b_title");

    public final StringPath b_type = createString("b_type");

    public final NumberPath<Integer> inquiry_num = createNumber("inquiry_num", Integer.class);

    public final NumberPath<Integer> lev = createNumber("lev", Integer.class);

    public final StringPath member_id = createString("member_id");

    public final DateTimePath<java.time.LocalDateTime> postDate = createDateTime("postDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> ref = createNumber("ref", Integer.class);

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public QInquiryEntity(String variable) {
        super(InquiryEntity.class, forVariable(variable));
    }

    public QInquiryEntity(Path<? extends InquiryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInquiryEntity(PathMetadata metadata) {
        super(InquiryEntity.class, metadata);
    }

}

